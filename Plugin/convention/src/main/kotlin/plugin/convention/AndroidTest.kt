/*
 * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */
package plugin.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.get
import plugin.convention.companion.requirePlugin
import plugin.convention.companion.versionCatalog
import plugin.convention.companion.withKotlinMultiplatformExtension
import plugin.convention.companion.withPluginManager

class AndroidTest : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            val libs = versionCatalog

            withPluginManager {
                requirePlugin("com.google.devtools.ksp") { apply(it) }
                requirePlugin("org.jetbrains.kotlin.plugin.allopen") { apply(it) }
            }

            withKotlinMultiplatformExtension {
                sourceSets.getByName("androidUnitTest").dependencies {
                    implementation(libs.findLibrary("mockative").get())
                }
                sourceSets.getByName("androidUnitTest") {
                    kotlin.srcDir("test")
                }
            }

            dependencies {
                configurations
                    .filter { it.name.startsWith("ksp") && it.name.contains("Test") }
                    .forEach {
                        add(it.name, libs.findLibrary("mockative").get())
                    }
            }
        }
    }
}
