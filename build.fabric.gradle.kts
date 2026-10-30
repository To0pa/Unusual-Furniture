@file:Suppress("UnstableApiUsage")

plugins {
    id("dev.kikugie.loom-back-compat")
    id("dev.kikugie.postprocess.jsonlang")
    id("me.modmuss50.mod-publish-plugin")
    id("maven-publish")
    id("unusual_furniture.package-info")
}

val dotenv = rootProject.extra["dotenv"] as? Map<*, *> ?: emptyMap<String, String>()
fun env(name: String): String? =
    providers.environmentVariable(name).orNull ?: (dotenv[name] as? String)

val minecraft = stonecutter.current.version
val mcVersion = stonecutter.current.project.substringBeforeLast('-')

tasks.named<ProcessResources>("processResources") {
    fun prop(name: String) = project.property(name) as String

    val props = HashMap<String, String>().apply {
        this["version"] = prop("mod.version")
        this["minecraft"] = prop("mod.mc_dep_fabric")
        this["javaVersion"] = if (stonecutter.eval(stonecutter.current.version, ">=26.1")) "JAVA_25" else "JAVA_21"
    }

    filesMatching(
        listOf(
            "fabric.mod.json",
            "META-INF/neoforge.mods.toml",
            "META-INF/mods.toml",
            "${prop("mod.id")}.mixins.json"
        )
    ) {
        expand(props)
    }

}

tasks.named("processResources") {
    dependsOn(":${stonecutter.current.project}:stonecutterGenerate")
}

version = "${property("mod.version")}+${property("deps.minecraft")}-fabric"
base.archivesName = property("mod.id") as String

//loom {
//    accessWidenerPath = rootProject.file("src/main/resources/${property("mod.id")}.accesswidener")
//}

jsonlang {
    languageDirectories = listOf("assets/${property("mod.id")}/lang")
    prettyPrint = true
}

repositories {
    mavenLocal()
    mavenCentral()
    exclusiveContent {
        forRepository { maven("https://api.modrinth.com/maven") }
        filter { includeGroup("maven.modrinth") }
    }
    exclusiveContent {
        forRepository { maven("https://cursemaven.com") }
        filter { includeGroup("curse.maven") }
    }
    exclusiveContent {
        forRepository { maven("https://maven.parchmentmc.org") }
        filter { includeGroupAndSubgroups("org.parchmentmc") }
    }
    exclusiveContent {
        forRepository { maven("https://maven.terraformersmc.com/releases/") }
        filter { includeGroupAndSubgroups("com.terraformersmc") }
    }
    exclusiveContent {
        forRepository { maven("https://maven.gegy.dev") }
        filter {
            includeGroupAndSubgroups("dev.lambdaurora")
            includeGroup("io.github.queerbric")
        }
    }
    exclusiveContent {
        forRepository { maven("https://repo.sleeping.town") }
        filter { includeGroup("dev.emi") }
    }
}

dependencies {
    minecraft("com.mojang:minecraft:${property("deps.minecraft")}")
    loomx.applyMojangMappings()
    modImplementation("net.fabricmc:fabric-loader:${property("deps.fabric-loader")}")

    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric_api")}")
    compileOnly("org.jspecify:jspecify:1.0.0")

    if (hasProperty("deps.modmenu")) {
        modLocalRuntime("com.terraformersmc:modmenu:${property("deps.modmenu")}")
    }

    // common optimization mods to test
    modLocalRuntime("maven.modrinth:sodium:mc1.21.1-0.8.13-fabric")
    modLocalRuntime("maven.modrinth:ferrite-core:7.0.3-fabric")
    modLocalRuntime("maven.modrinth:lithium:mc1.21.1-0.15.4-fabric")
    modLocalRuntime("maven.modrinth:modernfix:5.25.1+mc1.21.1")
    modLocalRuntime("dev.lambdaurora.lambdynamiclights:lambdynamiclights-runtime:4.8.11+1.21.1")
    modLocalRuntime("maven.modrinth:scalablelux:0.3.0-alpha.0.7+1.21.1")
    modLocalRuntime("dev.emi:emi-fabric:1.1.24+1.21.1")
}

configurations.all {
    resolutionStrategy {
        force("net.fabricmc:fabric-loader:${property("deps.fabric-loader")}")
    }
}

stonecutter {
    replacements.string {
        direction = eval(current.version, ">1.21.10")
        replace("ResourceLocation", "Identifier")
    }
}

tasks {
    processResources {
        exclude("**/neoforge.mods.toml", "**/mods.toml")
    }

    register<Copy>("buildAndCollect") {
        group = "build"
        from(loomx.modJar.map { it.archiveFile })
        into(rootProject.layout.buildDirectory.file("libs/${project.property("mod.version")}"))
        dependsOn("build")
    }
}

fabricApi {
    configureDataGeneration {
        outputDirectory = file("$rootDir/src/main/generated")
        client = true
    }
}

java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

val additionalVersionsStr = findProperty("publish.additionalVersions") as String?
val additionalVersions: List<String> = additionalVersionsStr
    ?.split(",")
    ?.map { it.trim() }
    ?.filter { it.isNotEmpty() }
    ?: emptyList()

publishMods {
    file = loomx.modJar.map { it.archiveFile.get() }

    // one of BETA, ALPHA, STABLE
    type = STABLE
    displayName = "${property("mod.name")} ${property("mod.version")} for ${stonecutter.current.version} Fabric"
    version = "${property("mod.version")}+${property("deps.minecraft")}-fabric"
    changelog = provider { rootProject.file("CHANGELOG-LATEST.md").readText() }
    modLoaders.add("fabric")
    modLoaders.add("quilt")

    modrinth {
        additionalFile(loomx.modSourcesJar) {
            type.set(SOURCES_JAR)
        }
        projectId = property("publish.modrinth") as String
        accessToken = env("MODRINTH_API_KEY")
        environment = CLIENT_AND_SERVER
        minecraftVersions.add(property("deps.minecraft").toString())
        minecraftVersions.addAll(additionalVersions)
        requires("fabric-api")
    }

    curseforge {
        additionalFiles.from(loomx.modSourcesJar.map { it.archiveFile.get() })
        projectId = property("publish.curseforge") as String
        accessToken = env("CURSEFORGE_API_KEY")
        client = true
        server = true
        minecraftVersions.add(stonecutter.current.version)
        minecraftVersions.addAll(additionalVersions)
        requires("fabric-api")
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = property("mod.group") as String
            artifactId = (property("mod.id") as String) + "-fabric"
            version = (property("mod.version") as String) + "+${property("deps.minecraft")}"
            from(components["java"])
        }
    }
    repositories {
        mavenLocal()
        maven {
            name = "macuguita"
            url = uri("https://maven.macuguita.com/releases")

            credentials {
                username = env("MAVEN_USERNAME")
                password = env("MAVEN_KEY")
            }
        }
    }
}
