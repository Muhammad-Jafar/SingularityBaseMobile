package dashboard

import core.navigation.NavigationRoute

data class DashboardRoute(
    override val payload: DashboardRoutePayload = DashboardRoutePayload(),
) : NavigationRoute<DashboardRoutePayload> {
    companion object {
        const val ROUTE = "dashboard"
    }

    override fun route(): String = ROUTE
}

data class DashboardRoutePayload(
    val unit: Unit = Unit,
)
