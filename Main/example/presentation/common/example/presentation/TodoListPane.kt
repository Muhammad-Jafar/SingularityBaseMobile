/*
 * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */
package example.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import common.PrettyJson
import common.StateSaver
import core.ui.SingularityScope
import core.ui.designsystem.SingularityTheme
import core.ui.designsystem.component.SLargeCard
import core.ui.designsystem.component.SLargeSpacing
import core.ui.designsystem.component.SLazyColumn
import core.ui.designsystem.component.SMediumSpacing
import core.ui.designsystem.component.SPrimaryButton
import core.ui.designsystem.component.SSearchComponent
import core.ui.designsystem.component.STextBody
import core.ui.designsystem.component.STextLabel
import core.ui.designsystem.component.STextTitle
import core.ui.designsystem.`large-padding`
import core.ui.designsystem.`medium-padding`
import example.model.Context
import example.model.TodoID
import example.presentation.entity.TodoDisplay
import example.presentation.entity.TodoFilter
import kotlinx.serialization.encodeToString

@Immutable
data class TodoListPanePld(
    val unit: Unit = Unit,
)

context(SingularityScope, Context)
@Composable
fun TodoListPane(
    pld: TodoListPanePld,
    stateSaver: StateSaver = StateSaver(),
    viewModel: TodoListPaneViewModel = viewModel<TodoListPaneViewModel>(
        factory = viewModelFactory {
            initializer {
                TodoListPaneViewModel(
                    this@Context,
                    stateSaver.pop() ?: TodoListPaneState.SaveAble()
                )
            }
        }
    ),
    goToTodoDetail: (TodoID) -> Unit
) {

    val attr = SingularityTheme.attr
    val states = viewModel.reducer
    val todoListDisplay by states.todoListDisplay.collectAsState(listOf())
    val errorDisplay2 by states.listErrorDisplay.collectAsState("")
    val scrollState = rememberLazyListState()

    // preload
    LaunchedEffect(
        states.dataIsNotLoaded
    ) {
        viewModel.Post(Intent.Reload)
    }

    LaunchedEffect(todoListDisplay) {
        val selectedIndex = todoListDisplay
            .indexOfFirst { it.selected }
            .let { if (it < 0) null else it }

        if (selectedIndex != null)
            scrollState.animateScrollToItem(selectedIndex)
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .imePadding()
    ) {

        val platformName by states.platformName.collectAsState("")
        STextTitle(
            text = platformName,
            modifier = Modifier.padding(horizontal = attr.`large-padding`)
        )

        val status by states.statusDisplay.collectAsState("")
        Status(status = status)

        val errorDisplay by states.errorDisplay.collectAsState("")
        Error(error = errorDisplay)

        val appliedFilters by states.appliedFilters.collectAsState("")
        AppliedFilters(appliedFilters = appliedFilters)

        SLargeSpacing()
        var clue by remember { mutableStateOf(states.searchClue.value) }
        SSearchComponent(
            clue,
            onSearch = { q: String ->
                clue = q
                viewModel.Post(Intent.Search(q))
            }
        )

        SMediumSpacing()
        ButtonFilters(
            onFilter = { filter: TodoFilter ->
                val isAlreadySelected = states.todoFilters.value.contains(filter)
                if (isAlreadySelected)
                    viewModel.Post(Intent.RemoveFilter(filter))
                else
                    viewModel.Post(Intent.AddFilter(filter))
            },
            onClearFilter = { viewModel.Post(Intent.ClearFilter) }
        )
        SMediumSpacing()
        TodoList(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            todoListDisplay = todoListDisplay,
            scrollState = scrollState,
            error = errorDisplay2,
            onReload = { viewModel.Post(Intent.Reload) },
            onItemClicked = { todoDisplay: TodoDisplay ->
                val isAlreadySelected = states.selectedTodoIDs.value.contains(todoDisplay.todoID)
                if (isAlreadySelected) {
                    // save current state
                    stateSaver.pushOrAmend(viewModel.state.saveAble())
                    goToTodoDetail.invoke(todoDisplay.todoID)
                } else {
                    viewModel.Post(Intent.SelectTodo(todoDisplay.todoID))
                }
            },
        )
    }
}

@Composable
private fun Status(
    status: String
) {
    val attr = SingularityTheme.attr
    STextLabel(
        text = status,
        modifier = Modifier.padding(horizontal = attr.`large-padding`)
    )
}

@Composable
private fun Error(
    error: String
) {
    val attr = SingularityTheme.attr
    STextLabel(
        text = error,
        modifier = Modifier.padding(horizontal = attr.`large-padding`)
    )
}

@Composable
private fun AppliedFilters(
    appliedFilters: String
) {
    val attr = SingularityTheme.attr
    STextLabel(
        text = appliedFilters,
        modifier = Modifier.padding(horizontal = attr.`large-padding`)
    )
}

context(SingularityScope)
@Composable
private fun TodoList(
    modifier: Modifier,
    scrollState: LazyListState,
    todoListDisplay: List<TodoDisplay>,
    error: String,
    onReload: () -> Unit,
    onItemClicked: (TodoDisplay) -> Unit,
) {
    SLazyColumn(
        modifier = modifier,
        state = scrollState,
    ) {
        items(todoListDisplay) {
            TodoItem(
                item = it,
                onClick = onItemClicked
            )
        }

        item {
            SLargeSpacing()
        }

        item(error) {
            if (error.isNotBlank())
                Reload(
                    error = error,
                    onReload = onReload
                )
        }
    }
}

@Composable
fun Reload(
    error: String,
    onReload: () -> Unit
) {
    val attr = SingularityTheme.attr

    Box(
        modifier = Modifier
            .padding(horizontal = attr.`large-padding`)
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(attr.`large-padding`)
            ) {
                STextLabel(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    text = error
                )
                Button(
                    modifier = Modifier
                        .padding(top = attr.`large-padding`)
                        .align(Alignment.End),
                    onClick = onReload
                ) {
                    STextLabel(
                        text = "Reload"
                    )
                }
            }
        }
    }
}

context(SingularityScope)
@Composable
private fun ButtonFilters(
    onFilter: (TodoFilter) -> Unit,
    onClearFilter: () -> Unit
) {
    Row {

        SLargeSpacing()
        SPrimaryButton(
            onClick = { onFilter.invoke(TodoFilter.ShowCompleteOnly) }
        ) {
            STextLabel(
                text = "Completed"
            )
        }

        SMediumSpacing()
        SPrimaryButton(
            onClick = { onClearFilter.invoke() }
        ) {
            STextLabel(
                text = "Show All"
            )
        }

    }
}

context(SingularityScope)
@Composable
private fun TodoItem(
    item: TodoDisplay,
    onClick: (TodoDisplay) -> Unit,
) {
    val attr = SingularityTheme.attr
    Column(
        modifier = Modifier
            .padding(horizontal = attr.`large-padding`)
    ) {
        TodoCard(
            todo = item,
            onClick = onClick
        )
        SLargeSpacing()
    }
}

context(SingularityScope)
@Composable
private fun TodoCard(
    todo: TodoDisplay,
    onClick: (TodoDisplay) -> Unit
) {
    val attr = SingularityTheme.attr
    SLargeCard(
        onClick = { onClick.invoke(todo) },
        colors = if (todo.selected)
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        else
            CardDefaults.cardColors(),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(attr.`large-padding`)
        ) {
            Column {
                STextBody(
                    text = PrettyJson
                        .encodeToString(todo)
                )

                if (todo.selected)
                    SMediumSpacing()

                AnimatedVisibility(todo.selected) {
                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        STextLabel(
                            text = "Click again to open detail",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(
                                    horizontal = attr.`large-padding`,
                                    vertical = attr.`medium-padding`
                                )
                        )
                    }
                }
            }
        }
    }
}
