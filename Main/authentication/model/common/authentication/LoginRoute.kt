import core.navigation.NavigationRoute

data class LoginRoute(
    override val payload: LoginRoutePayload = LoginRoutePayload(),
) : NavigationRoute<LoginRoutePayload> {
    companion object {
        const val ROUTE = "login"
    }

    override fun route(): String = ROUTE
}

data class LoginRoutePayload(
    val unit: Unit = Unit,
)
