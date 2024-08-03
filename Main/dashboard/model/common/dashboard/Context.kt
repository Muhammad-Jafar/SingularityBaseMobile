package dashboard

import ai_chat.Context as AIChatContext
import todolist.Context as TodoListContext

data class Context(
    val aiChatContext: AIChatContext,
    val todoListContext: TodoListContext,
)
