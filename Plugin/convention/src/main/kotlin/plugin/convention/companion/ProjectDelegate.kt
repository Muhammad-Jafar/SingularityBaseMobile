package plugin.convention.companion

import org.gradle.kotlin.dsl.support.delegates.ProjectDelegate

fun ProjectDelegate.taskIsRunningTest() =
    gradle.startParameter.taskNames
        .any { it == "check" || it.startsWith("test") || it.contains("Test", true) }
