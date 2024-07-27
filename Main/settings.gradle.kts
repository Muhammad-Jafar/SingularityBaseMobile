/*
 * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        includeBuild("../Plugin")
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        includeBuild("../System") {
            dependencySubstitution {
                // include all Library
                File(settingsDir, "../System")
                    .listFiles()
                    ?.asSequence()
                    ?.filter { it.isDirectory }
                    ?.filter { it.listFiles()?.map { it.name }?.contains("build.gradle.kts") == true }
                    ?.onEach { dir ->
                        substitute(module("system:${dir.name}")).using(project(":${dir.name}"))
                    }?.toList()
            }
        }
        includeBuild("../Shared") {
            dependencySubstitution {
                // include all Library
                File(settingsDir, "../Shared")
                    .listFiles()
                    ?.asSequence()
                    ?.filter { it.isDirectory }
                    ?.filter { it.listFiles()?.map { it.name }?.contains("build.gradle.kts") == true }
                    ?.onEach { dir ->
                        substitute(module("shared:${dir.name}")).using(project(":${dir.name}"))
                    }?.toList()
            }
        }
    }
}

fun includeModuleRecursively(name: String) {
    File(settingsDir, "./$name")
        .listFiles()
        ?.asSequence()
        ?.filter { it.isDirectory }
        ?.filter { it.listFiles()?.map { it.name }?.contains("build.gradle.kts") == true }
        ?.onEach { dir ->
            include(":$name:${dir.name}")
        }?.toList()
}

include(":composeApp")
includeModuleRecursively("todolist")
includeModuleRecursively("ai_chat")
includeModuleRecursively("dashboard")
includeModuleRecursively("authentication")
