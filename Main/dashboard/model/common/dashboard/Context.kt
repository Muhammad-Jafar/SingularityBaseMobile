package dashboard

import ai_chat.Context as AIChatContext
import todolist.Context as ExampleContext

data class Context(
    val aiChatContext: AIChatContext,
    val exampleContext: ExampleContext,
)
