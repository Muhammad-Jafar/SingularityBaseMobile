package ai_chat

import ai_chat.entity.ChatHistoryItemDisplay
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import common.StateSaver
import common.getPlatform
import common.isAndroid
import common.isIOS
import core.ui.SingularityPane
import core.ui.SingularityScope
import core.ui.designsystem.SingularityTheme
import core.ui.designsystem.component.SErrorSnackBar
import core.ui.designsystem.component.SIconButton
import core.ui.designsystem.component.SLargeSpacing
import core.ui.designsystem.component.SMediumSpacing
import core.ui.designsystem.component.STextBody
import core.ui.designsystem.component.STextLabel
import core.ui.designsystem.component.STextTitle
import core.ui.designsystem.`large-padding`
import core.ui.designsystem.`medium-padding`
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import system.designsystem.resources.Res
import system.designsystem.resources.groot

@Immutable
data class AIChatPanePld(
    val unit: Unit = Unit
)

context(SingularityScope, Context)
@Composable
fun AIChatPane(
    pld: AIChatPanePld,
    stateSaver: StateSaver,
    viewModel: AIChatPaneViewModel = viewModel<AIChatPaneViewModel>(
        factory = viewModelFactory {
            initializer {
                AIChatPaneViewModel(
                    defaultSate = stateSaver.pop() ?: AIChatPaneState()
                )
            }
        }
    ),
    onBack: () -> Unit
) {
    val attr = SingularityTheme.attr
    val scope = rememberCoroutineScope()
    val states by viewModel.container.stateFlow.collectAsState()
    val platform = remember { getPlatform() }

    SingularityPane {
        Column {
            val listState = rememberLazyListState()
            Row(
                modifier = Modifier.padding(
                    start = attr.`medium-padding`,
                    top = attr.`medium-padding`
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SIconButton(
                    onClick = onBack
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
                Image(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(Res.drawable.groot),
                    contentDescription = "Groot customer service"
                )
                SMediumSpacing()
                STextTitle("I am Groot!")
            }
            SMediumSpacing()

            val chatHistoryItem by remember(states) {
                derivedStateOf {
                    states.chatHistories
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
                    .weight(1f)
                    .background(
                        MaterialTheme.colorScheme.surfaceContainer
                    ),
                state = listState
            ) {
                item {
                    SMediumSpacing()
                }
                items(chatHistoryItem.size) { index ->
                    val item = chatHistoryItem[index]
                    PairChatBlock(historyItem = item)
                    SMediumSpacing()
                }
                item {
                    SLargeSpacing()
                }
            }

            LaunchedEffect(chatHistoryItem) {
                if (chatHistoryItem.isEmpty())
                    return@LaunchedEffect

                listState.animateScrollToItem(
                    chatHistoryItem.size - 1 + 1
                    /**because we have spacer**/
                    /**because we have spacer**/
                )
            }

            SMediumSpacing()

            var prompt by remember { mutableStateOf("") }
            TextField(
                modifier = Modifier
                    .padding(
                        horizontal = attr.`large-padding`
                    )
                    .fillMaxWidth(),
                value = prompt,
                placeholder = {
                    STextLabel("Enter Prompt")
                },
                onValueChange = { prompt = it },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (prompt.isBlank()) return@KeyboardActions
                        viewModel.chat(message = prompt)
                        prompt = ""
                    }
                )
            )

            when {
                platform.isAndroid() -> {
                    SLargeSpacing()
                    SLargeSpacing()
                }

                platform.isIOS() -> {
                    SLargeSpacing()
                    SLargeSpacing()
                }

                else -> {
                    SLargeSpacing()
                }
            }
        }

        var snackBarMessage by remember { mutableStateOf("") }
        if (snackBarMessage.isNotBlank())
            SErrorSnackBar(
                message = snackBarMessage,
                actionLabel = "ok",
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                snackBarMessage = ""
            }

        LaunchedEffect(viewModel.container) {
            scope.launch {
                viewModel.container.sideEffectFlow.collect {
                    when (it) {
                        is AIChatPaneSideEffect.ShowToast -> {
                            snackBarMessage = it.message.lines().first()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PairChatBlock(
    historyItem: ChatHistoryItemDisplay
) {
    Column {
        ChatBlock(message = historyItem.message)
        SMediumSpacing()
        ChatAnswerBlock(message = historyItem.answer)
    }
}

@Composable
fun ChatBlock(
    message: String
) {
    val attr = SingularityTheme.attr
    Box(
        modifier = Modifier
            .padding(
                horizontal = attr.`large-padding`
            )
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .align(Alignment.CenterEnd),
            colors = CardDefaults.cardColors().copy(
                contentColor = MaterialTheme.colorScheme.onSurface,
                containerColor = MaterialTheme.colorScheme.surfaceContainerLow
            )
        ) {
            STextBody(
                text = message,
                modifier = Modifier
                    .padding(
                        horizontal = attr.`large-padding`,
                        vertical = attr.`medium-padding`
                    )
            )
        }
    }
}

@Composable
fun ChatAnswerBlock(
    message: String
) {
    val attr = SingularityTheme.attr
    Box(
        modifier = Modifier
            .padding(
                horizontal = attr.`large-padding`
            )
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .align(Alignment.CenterStart),
            colors = CardDefaults.cardColors().copy(
                contentColor = MaterialTheme.colorScheme.onSurface,
                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
            )
        ) {
            STextBody(
                text = message,
                modifier = Modifier
                    .padding(
                        horizontal = attr.`large-padding`,
                        vertical = attr.`medium-padding`
                    )
            )
        }
    }
}
