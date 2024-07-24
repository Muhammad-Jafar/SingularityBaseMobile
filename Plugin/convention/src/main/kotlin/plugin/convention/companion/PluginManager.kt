package plugin.convention.companion

import org.gradle.api.plugins.PluginManager

class MissingPluginException(id: String) : Exception("Missing required plugin with id: $id")

fun PluginManager.requirePlugin(
    id: String,
    orElse: PluginManager.(String) -> Unit = { throw MissingPluginException(it) }
) {
    if (!hasPlugin(id))
        orElse(id)
}

fun PluginManager.requireAtLeastOne(
    ids: List<String>,
    orElse: PluginManager.(List<String>) -> Unit = { throw MissingPluginException(it.joinToString(",")) }
) {
    val atLeastOneApplied = ids.map { hasPlugin(it) }.fold(false) { v, acc -> v || acc }
    if (!atLeastOneApplied)
        orElse.invoke(this, ids)
}

