package ai_chat

import core.navigation.NavigationRoute

data class AIChatRoute(
    override val payload: AIChatPayload = AIChatPayload(),
) : NavigationRoute<AIChatPayload> {
    companion object {
        const val ROUTE = "aiChat"
    }

    override fun route(): String = ROUTE
}

data class AIChatPayload(
    val unit: Unit = Unit,
)
