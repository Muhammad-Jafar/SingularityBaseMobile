package main

import ProjectConfig
import main.modules.TodoListWebRepositoryContext
import ai_chat.Context as AIChatContext
import authentication.Context as AuthenticationContext
import dashboard.Context as DashboardContext
import todolist.Context as TodoListContext

// Centralized context control
class MainContext {
    // ExampleModule's Context
    val todoListContext: TodoListContext by lazy {
        TodoListContext(
            webRepositoryContext =
                TodoListWebRepositoryContext(
                    ProjectConfig.HOST,
                    ProjectConfig.TODO_API_BASE_PATH,
                ),
        )
    }

    val aiChatContext: AIChatContext by lazy {
        AIChatContext(
            geminiApiKey = ProjectConfig.GEMINI_API_KEY,
        )
    }

    val dashboardContext: DashboardContext by lazy {
        DashboardContext(
            todoListContext = todoListContext,
            aiChatContext = aiChatContext,
        )
    }

    val authenticationContext: AuthenticationContext by lazy {
        AuthenticationContext()
    }
}
