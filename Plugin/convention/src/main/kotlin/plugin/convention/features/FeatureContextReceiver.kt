/*
 * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */
package plugin.convention.features

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import plugin.convention.companion.withKotlinMultiplatformExtension

class FeatureContextReceiver : Plugin<Project> {

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    override fun apply(project: Project) {
        with(project) {
            withKotlinMultiplatformExtension {
                compilerOptions {
                    freeCompilerArgs.add("-Xcontext-receivers")
                }
            }
        }
    }
}