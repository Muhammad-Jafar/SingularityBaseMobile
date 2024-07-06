package core.util.systemlayer

import androidx.compose.runtime.Immutable

@Immutable
interface SystemLoggerScope {
    fun log(message: String)
}

@Immutable
class SystemLoggerScopeImpl : SystemLoggerScope {
    override fun log(message: String) {
        println("Singularity Log: $message")
    }
}