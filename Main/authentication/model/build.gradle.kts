plugins {
    id("LibraryConventionV1")
    id("CompileIOS")
    id("CompileWasm")
    id("FeatureCoroutine")
    id("FeatureSerialization")
}

android {
    namespace = "main.authentication.model"
}

task("testClasses")
