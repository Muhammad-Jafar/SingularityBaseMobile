package core.util.systemlayer

interface SystemLoggerScope {
    fun log(message: String)
}

class SystemLoggerScopeImpl : SystemLoggerScope {
    override fun log(message: String) {
        println("Singularity Log: $message")
    }
}