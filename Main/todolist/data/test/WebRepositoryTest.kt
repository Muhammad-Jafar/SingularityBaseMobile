package example.data

import core.context.WebClient
import core.context.WebRepositoryContext
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.every
import io.mockative.mock
import kotlinx.coroutines.runBlocking
import todolist.GetTodos
import kotlin.test.Test
import kotlin.test.assertFails
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class WebRepositoryTest {
    @Mock
    val context = mock(classOf<WebRepositoryContext>())

    @Mock
    val webClient = mock(classOf<WebClient>())

    @Test
    fun `get-todos-success`() {
        every { context.webClient }.invokes { webClient }

        runBlocking {
            val response =
                """
                [
                    {
                      "userId": 1,
                      "id": 1,
                      "title": "delectus aut autem",
                      "completed": false
                    },
                    {
                      "userId": 1,
                      "id": 2,
                      "title": "quis ut nam facilis et officia qui",
                      "completed": false
                    },
                    {
                      "userId": 1,
                      "id": 3,
                      "title": "fugiat veniam minus",
                      "completed": false
                    }
                ]
                """.trimIndent()

            coEvery { webClient.get("todos/") }.invokes { Result.success(suspend { response.toByteArray() }) }

            val result = with(context) { GetTodos() }

            assertTrue { result.isSuccess }
            assertTrue { result.getOrNull()?.size == 3 }
            assertTrue { result.getOrThrow().first().title == "delectus aut autem" }
        }
    }

    @Test
    fun `get-todos-fail`() {
        every { context.webClient }.invokes { webClient }

        runBlocking {
            coEvery { webClient.get("todos/") }.invokes { Result.failure(Exception("x error")) }

            val result = with(context) { GetTodos() }

            assertFalse { result.isSuccess }
            assertFails { result.getOrThrow() }
        }
    }
}
