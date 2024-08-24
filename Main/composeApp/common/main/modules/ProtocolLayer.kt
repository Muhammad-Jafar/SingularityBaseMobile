package main.modules

import core.layer.AuthenticationException
import core.layer.SystemException
import core.layer.SystemProtocolLayer

class ProtocolLayer(
    private val fallback: (SystemException) -> Unit,
) : SystemProtocolLayer {
    override fun catchAuthenticationException(e: Exception?): Exception? {
        if (e is AuthenticationException) {
            fallback.invoke(e)
            return null
        } else {
            return e
        }
    }

    override fun catchIdentificationException(e: Exception?): Exception? {
        if (e is AuthenticationException) {
            fallback.invoke(e)
            return null
        } else {
            return e
        }
    }

    override fun catchAccessControlException(e: Exception?): Exception? {
        if (e is AuthenticationException) {
            fallback.invoke(e)
            return null
        } else {
            return e
        }
    }
}
