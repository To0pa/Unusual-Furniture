plugins {
    id("dev.kikugie.stonecutter")
    id("net.fabricmc.fabric-loom-remap") version "1.19-SNAPSHOT" apply false
    id("net.neoforged.moddev") version "2.0.148" apply false
    id("dev.kikugie.postprocess.jsonlang") version "2.1-beta.4" apply false
    id("me.modmuss50.mod-publish-plugin") version "2.2.0" apply false
}

val dotenv = java.util.Properties().apply {
    val dotenvFile = file(".env")
    if (dotenvFile.isFile) {
        dotenvFile.reader(Charsets.UTF_8).use { load(it) }
    }
}
rootProject.extra["dotenv"] = dotenv.stringPropertyNames().associateWith { dotenv.getProperty(it) }

stonecutter active "1.21.1-fabric"

stonecutter parameters {
    constants.match(node.metadata.project.substringAfterLast('-'), "fabric", "neoforge")
}

stonecutter tasks {
    order("publishModrinth")
    order("publishCurseforge")
}

for (version in stonecutter.versions.map { it.version }.distinct()) tasks.register("publish$version") {
    group = "publishing"
    dependsOn(stonecutter.tasks.named("publishMods") { metadata.version == version })
}
