@file:Suppress("UnstableApiUsage")

plugins {
    id("net.neoforged.moddev")
    id("dev.kikugie.postprocess.jsonlang")
    id("me.modmuss50.mod-publish-plugin")
    id("maven-publish")
    id("unusual_furniture.package-info")
}

val dotenv = rootProject.extra["dotenv"] as? Map<*, *> ?: emptyMap<String, String>()
fun env(name: String): String? =
    providers.environmentVariable(name).orNull ?: (dotenv[name] as? String)

tasks.named<ProcessResources>("processResources") {
    fun prop(name: String) = project.property(name) as String

    val props = HashMap<String, String>().apply {
        this["version"] = prop("mod.version")
        this["minecraft"] = prop("mod.mc_dep_forgelike")
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

version = "${property("mod.version")}+${property("deps.minecraft")}-neoforge"
base.archivesName = property("mod.id") as String

jsonlang {
    languageDirectories = listOf("assets/${property("mod.id")}/lang")
    prettyPrint = true
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://maven.neoforged.net/releases")
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
        forRepository { maven("https://maven.gegy.dev") }
        filter {
            includeGroupAndSubgroups("dev.lambdaurora")
            includeGroup("io.github.queerbric")
        }
    }
}

val localRuntime = configurations.create("localRuntime")

configurations {
    runtimeClasspath {
        extendsFrom(localRuntime)
    }
}

neoForge {
    version = property("deps.neoforge") as String
    validateAccessTransformers = true

    if (hasProperty("deps.parchment")) parchment {
        val (mc, ver) = (property("deps.parchment") as String).split(':')
        mappingsVersion = ver
        minecraftVersion = mc
    }

    runs {
        register("client") {
            gameDirectory = file("run/")
            client()
        }
        register("server") {
            gameDirectory = file("run/")
            server()
        }
    }

    mods {
        register(property("mod.id") as String) {
            sourceSet(sourceSets["main"])
        }
    }
    sourceSets["main"].resources.srcDir("src/main/generated")
}

val mappingsAttribute = Attribute.of("net.minecraft.mappings", String::class.java)

dependencies {
    attributesSchema {
        attribute(mappingsAttribute)
    }

    compileOnly("org.jspecify:jspecify:1.0.0")

    // common optimization mods to test
    localRuntime("maven.modrinth:sodium:mc1.21.1-0.8.13-neoforge")
    localRuntime("maven.modrinth:ferrite-core:7.0.3-neoforge")
    localRuntime("maven.modrinth:lithium:mc1.21.1-0.15.4-neoforge")
    localRuntime("maven.modrinth:modernfix:5.27.27+mc1.21.1")
    localRuntime("dev.lambdaurora.lambdynamiclights:lambdynamiclights-runtime:4.8.11+1.21.1") {
        attributes {
            attribute(mappingsAttribute, "mojmap")
        }
    }
    localRuntime("maven.modrinth:scalablelux:0.3.0-alpha.0.8+1.21.1")
}

tasks {
    processResources {
        exclude("**/fabric.mod.json", "**/*.accesswidener", "**/mods.toml")
    }

    named("createMinecraftArtifacts") {
        dependsOn("stonecutterGenerate")
    }

    register<Copy>("buildAndCollect") {
        group = "build"
        from(jar.map { it.archiveFile })
        into(rootProject.layout.buildDirectory.file("libs/${project.property("mod.version")}"))
        dependsOn("build")
    }
}

java {
    withSourcesJar()
    val javaCompat = if (stonecutter.eval(stonecutter.current.version, ">=26.1")) {
        JavaVersion.VERSION_25
    } else {
        JavaVersion.VERSION_21
    }
    sourceCompatibility = javaCompat
    targetCompatibility = javaCompat
}

val additionalVersionsStr = findProperty("publish.additionalVersions") as String?
val additionalVersions: List<String> = additionalVersionsStr
    ?.split(",")
    ?.map { it.trim() }
    ?.filter { it.isNotEmpty() }
    ?: emptyList()

publishMods {
    file = tasks.jar.map { it.archiveFile.get() }
    additionalFiles.from(tasks.named<org.gradle.jvm.tasks.Jar>("sourcesJar").map { it.archiveFile.get() })

    type = STABLE
    displayName = "${property("mod.name")} ${property("mod.version")} for ${stonecutter.current.version} NeoForge"
    version = "${property("mod.version")}+${property("deps.minecraft")}-neoforge"
    changelog = provider { rootProject.file("CHANGELOG-LATEST.md").readText() }
    modLoaders.add("neoforge")

    modrinth {
        projectId = property("publish.modrinth") as String
        accessToken = env("MODRINTH_API_KEY")
        environment = CLIENT_AND_SERVER
        minecraftVersions.add(stonecutter.current.version)
        minecraftVersions.addAll(additionalVersions)
    }

    curseforge {
        projectId = property("publish.curseforge") as String
        accessToken = env("CURSEFORGE_API_KEY")
        client = true
        server = true
        minecraftVersions.add(stonecutter.current.version)
        minecraftVersions.addAll(additionalVersions)
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = property("mod.group") as String
            artifactId = (property("mod.id") as String) + "-neoforge"
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
