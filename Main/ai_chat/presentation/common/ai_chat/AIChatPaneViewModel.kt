package ai_chat

import ai_chat.entity.ChatHistoryItemDisplay
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import common.VmFailed
import common.VmSuccess
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

data class AIChatPaneState(
    val chatHistories: List<ChatHistoryItemDisplay> = listOf()
)

sealed class AIChatPaneSideEffect {
    data class ShowToast(val message: String) : AIChatPaneSideEffect()
}

class AIChatPaneViewModel(
    private val context: Context,
    defaultSate: AIChatPaneState,
) : ContainerHost<AIChatPaneState, AIChatPaneSideEffect>, ViewModel() {

    override val container: Container<AIChatPaneState, AIChatPaneSideEffect> =
        viewModelScope.container(defaultSate)

    fun chat(message: String) = intent {

        // submit to gemini
        val response = context.geminiAgent.sendMessage(message)
            .fold(
                onSuccess = {
                    VmSuccess(it)
                },
                onFailure = {
                    postSideEffect(AIChatPaneSideEffect.ShowToast(it.cause?.message ?: it.message ?: "Unknown Error"))
                    VmFailed(Exception(it))
                }
            )

        // new entity
        val responseEntity = ChatHistoryItemDisplay(
            ChatHistoryItem(
                message = message,
                answer = ChatResponse(response)
            )
        )

        // add new entity to list of the state
        reduce {
            state.copy(
                chatHistories = state.chatHistories.plus(responseEntity)
            )
        }
    }
}