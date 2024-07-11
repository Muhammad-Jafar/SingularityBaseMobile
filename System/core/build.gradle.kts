import org.jetbrains.compose.ComposeExtension

plugins {
    id("LibraryConventionV1")
    id("CompileIOS")
    /*id("CompileWasm")*/
    id("FeaturePane")
    id("FeatureCoroutine")
    id("FeatureContextReceiver")
}

android {
    namespace = "system.core"
}

extensions.getByType<ComposeExtension>().resources {
    publicResClass = true
    packageOfResClass = "system.designsystem.resources"
    generateResClass = always
}

task("testClasses")