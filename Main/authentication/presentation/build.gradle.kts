import plugin.convention.companion.Shared
import plugin.convention.companion.System
import plugin.convention.companion.model
import plugin.convention.companion.data

plugins {
    id("LibraryConventionV1")
    id("CompileIOS")
    /*id("CompileWasm")*/
    id("FeatureCoroutine")
    id("FeaturePane")
    id("FeatureSerialization")
    id("FeatureHttpClient")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            System("core")
            Shared("common")

            data("authentication")
            model("authentication")
        }
    }
}

android {

    namespace = "main.authentication.presentation"
}

task("testClasses")
