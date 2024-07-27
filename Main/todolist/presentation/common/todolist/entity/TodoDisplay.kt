package todolist.entity

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable
import todolist.Todo
import todolist.TodoID

@Immutable
@Serializable
data class TodoDisplay(
    val todo: Todo,
    val selectable: Boolean,
    val selected: Boolean,
) {
    val todoID get() = TodoID(todo.id.toString())
}
