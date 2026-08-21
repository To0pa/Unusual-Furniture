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
