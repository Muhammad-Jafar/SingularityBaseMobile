package main.modules

import core.util.context.WebClient
import core.util.context.WebRepositoryContext
import webclient.defaultWebClient

class ExampleWebRepositoryContext(
    private val host: String,
    private val basePath: String
) : WebRepositoryContext {

    override val webClient: WebClient by lazy {
        defaultWebClient(
            host,
            basePath
        )
    }
}
