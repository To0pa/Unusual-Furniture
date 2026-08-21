import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.provider.Property
import org.gradle.api.tasks.*
import java.io.File

listOf("main" to "Main", "client" to "Client").forEach { (dirName, taskSuffix) ->

    val srcDir = project.file("src/$dirName/java")

    val generateTask = tasks.register<GeneratePackageInfos>("generate${taskSuffix}PackageInfos") {
        group = "generation"
        if (srcDir.exists()) {
            sourceRoots.setFrom(srcDir)
        }
    }

    tasks.matching { it.name == "ideaSyncTask" }.configureEach {
        dependsOn(generateTask)
    }

    val cleanTask = tasks.register("clean${taskSuffix}PackageInfos") {
        group = "generation"
        doLast {
            if (srcDir.exists()) {
                srcDir.walkTopDown()
                    .filter { it.isFile && it.name == "package-info.java" }
                    .forEach { file ->
                        if (file.readText().contains("Auto-generated package-info")) {
                            file.delete()
                        }
                    }
            }
        }
    }

    tasks.matching { it.name == "clean" }.configureEach {
        dependsOn(cleanTask)
    }
}

abstract class GeneratePackageInfos : DefaultTask() {

    @get:Input
    abstract val projectName: Property<String>

    @get:InputFiles
    @get:SkipWhenEmpty
    abstract val sourceRoots: ConfigurableFileCollection

    init {
        projectName.convention(project.name)
    }

    @TaskAction
    fun run() {
        sourceRoots.files.forEach { root ->
            if (!root.exists() || !root.isDirectory) return@forEach

            root.walkTopDown().filter { it.isDirectory }.forEach { dir ->
                val containsSource = dir.listFiles()?.any {
                    it.isFile && (it.name.endsWith(".java") || it.name.endsWith(".kt"))
                } ?: false

                if (!containsSource) return@forEach

                val packageName = dir.relativeTo(root).path.replace(File.separatorChar, '.')
                val packageInfoFile = File(dir, "package-info.java")

                if (packageInfoFile.exists()) {
                    val text = packageInfoFile.readText()
                    if (!text.contains("Auto-generated package-info")) {
                        require(text.contains("@NullMarked")) {
                            "Manual package-info.java $packageInfoFile is missing @NullMarked annotation."
                        }
                        return@forEach
                    }
                }

                packageInfoFile.writeText("""
                    |/**
                    | * Auto-generated package-info for ${projectName.get()}.
                    | */
                    |@NullMarked
                    |package $packageName;
                    |
                    |import org.jspecify.annotations.NullMarked;
                    |""".trimMargin())
            }
        }
    }
}
