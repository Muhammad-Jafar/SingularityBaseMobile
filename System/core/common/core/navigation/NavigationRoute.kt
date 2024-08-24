package core.navigation

interface NavigationRoute<Payload : Any> {
    val payload: Payload

    fun route(): String
}
