import core.navigation.NavigationRoute

data class LoginRoute(
    override val route: String = ROUTE,
    override val payload: LoginRoutePayload = LoginRoutePayload(),
) : NavigationRoute<LoginRoutePayload> {
    companion object {
        const val ROUTE = "login"
    }
}

data class LoginRoutePayload(
    val unit: Unit = Unit,
)
