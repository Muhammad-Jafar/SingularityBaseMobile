package todolist.entity

sealed class TodoFilter {
    data object ShowCompleteOnly : TodoFilter()
}
