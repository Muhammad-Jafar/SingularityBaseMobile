/*
 * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */
package todolist

import core.context.WebClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

context(WebClient)
suspend fun GetTodos(): Result<List<Todo>> =
    withContext(Dispatchers.Default) {
        this@WebClient
            .get("todos/")
            .map {
                val stringResponse = it.invoke().decodeToString()
                Json.decodeFromString<List<Todo>>(stringResponse)
            }
    }
