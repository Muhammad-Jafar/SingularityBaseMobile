package plugin.convention.features

import org.gradle.api.Plugin
import org.gradle.api.Project
import plugin.convention.companion.requirePlugin
import plugin.convention.companion.withKotlinMultiplatformExtension
import plugin.convention.companion.withPluginManager

class FeatureOptics : Plugin<Project> {
    override fun apply(target: Project) {

        target.withPluginManager {
            requirePlugin("com.android.library") { apply(it) }
            requirePlugin("org.jetbrains.kotlin.android") { apply(it) }
            requirePlugin("com.google.devtools.ksp") { apply(it) }
        }
        val arrowVersion = "1.2.0"
        target.withKotlinMultiplatformExtension {
            sourceSets.commonMain.dependencies {
                implementation(platform("io.arrow-kt:arrow-stack:$arrowVersion"))
                implementation("io.arrow-kt:arrow-core")
                implementation("io.arrow-kt:arrow-optics")
            }
        }
    }

}
