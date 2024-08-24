import plugin.convention.companion.System
import plugin.convention.companion.model

plugins {
    id("LibraryConventionV1")
    id("CompileIOS")
    // id("CompileWasm")
    id("FeatureCoroutine")
    id("FeatureSerialization")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            System("core")

            model("ai_chat")
            model("todolist")
        }
    }
}

android {
    namespace = "main.dashboard.model"
}

task("testClasses")
