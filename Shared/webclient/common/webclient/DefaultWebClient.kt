package webclient

import core.util.context.WebClient

expect fun defaultWebClient(
    host: String,
    basePath: String
): WebClient