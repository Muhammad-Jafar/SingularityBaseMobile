import plugin.convention.companion.Shared
import plugin.convention.companion.System
import plugin.convention.companion.model

plugins {
    id("LibraryConventionV1")
    id("CompileIOS")
    /*id("CompileWasm")*/
    id("FeatureCoroutine")
    id("FeatureSerialization")
    id("FeatureHttpClient")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            System("core")
            Shared("common")

            model("ai_chat")
        }
    }
}

android {

    namespace = "main.ai_chat.data"
}

task("testClasses")
