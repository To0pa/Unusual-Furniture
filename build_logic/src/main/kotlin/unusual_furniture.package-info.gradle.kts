import java.nio.file.Files
import kotlin.io.path.createDirectories
import kotlin.io.path.exists
import kotlin.io.path.isDirectory
import kotlin.io.path.isRegularFile
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.writeText
import org.jetbrains.gradle.ext.settings
import org.jetbrains.gradle.ext.taskTriggers
import org.gradle.plugins.ide.idea.model.IdeaModel

plugins {
    java
}

rootProject.pluginManager.apply("idea")
rootProject.pluginManager.apply("org.jetbrains.gradle.plugin.idea-ext")

abstract class GeneratePackageInfos : DefaultTask() {

    @get:SkipWhenEmpty
    @get:InputFiles
    abstract val sourceRoots: ConfigurableFileCollection

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @get:Input
    abstract val projectName: Property<String>

    @TaskAction
    fun run() {
        val output = outputDir.get().asFile.toPath()

        sourceRoots.files
            .filter(File::isDirectory)
            .forEach { sourceRoot -> generateForRoot(sourceRoot.toPath(), output) }
    }

    private fun generateForRoot(sourceRoot: java.nio.file.Path, output: java.nio.file.Path) {
        Files.walk(sourceRoot).use { stream ->
            stream.filter { it.isDirectory() }.forEach { dir ->
                val containsSource = dir.listDirectoryEntries().any {
                    it.isRegularFile() &&
                            (it.fileName.toString().endsWith(".java") || it.fileName.toString().endsWith(".kt"))
                }
                if (!containsSource) return@forEach

                val relativePath = sourceRoot.relativize(dir)
                if (relativePath.toString().isEmpty()) return@forEach

                val existingPackageInfo = dir.resolve("package-info.java")
                if (existingPackageInfo.exists()) return@forEach

                val target = output.resolve(relativePath)
                target.createDirectories()

                val packageName = relativePath.toString().replace(File.separatorChar, '.')

                target.resolve("package-info.java").writeText(
                    """
                    |/**
                    | * Auto-generated package-info for ${projectName.get()}.
                    | */
                    |@NullMarked
                    |package $packageName;
                    |
                    |import org.jspecify.annotations.NullMarked;
                    |""".trimMargin()
                )
            }
        }
    }
}

// Hardcoded to the shared stonecutter source root
val mainSourceDir = rootProject.file("src/main/java")
val clientSourceDir = rootProject.file("src/client/java")

val mainOutputDir = rootProject.file("src/generated/main/java")
val clientOutputDir = rootProject.file("src/generated/client/java")

data class PackageInfoTarget(val sourceDir: File, val outputDir: File)

val targets = mapOf(
    "main" to PackageInfoTarget(mainSourceDir, mainOutputDir),
    "client" to PackageInfoTarget(clientSourceDir, clientOutputDir)
)

sourceSets.configureEach {
    val sourceSetName = name
    val target = targets[sourceSetName] ?: return@configureEach

    val generateTaskName = "generate${sourceSetName.replaceFirstChar { it.uppercase() }}PackageInfos"
    val cleanTaskName = "clean${sourceSetName.replaceFirstChar { it.uppercase() }}PackageInfos"

    val generateTask = rootProject.tasks.run {
        if (names.contains(generateTaskName)) named<GeneratePackageInfos>(generateTaskName)
        else register<GeneratePackageInfos>(generateTaskName) {
            group = "generation"
            description = "Generates package-info.java files for the $sourceSetName source set."
            sourceRoots.from(target.sourceDir)
            outputDir.set(target.outputDir)
            projectName.set(rootProject.name)
        }
    }

    val cleanTask = rootProject.tasks.run {
        if (names.contains(cleanTaskName)) named<Delete>(cleanTaskName)
        else register<Delete>(cleanTaskName) {
            group = "generation"
            delete(target.outputDir)
        }
    }

    java.srcDir(generateTask)
    tasks.matching { it.name == "compileJava" }.configureEach { dependsOn(generateTask) }
    tasks.matching { it.name == "sourcesJar" }.configureEach { dependsOn(generateTask) }
    tasks.named("clean") { dependsOn(cleanTask) }

    val ideaModel = rootProject.extensions.getByType<IdeaModel>()

    ideaModel.project.settings {
        taskTriggers {
            afterSync(generateTask)
        }
    }

    ideaModel.module.generatedSourceDirs.add(target.outputDir)
}
