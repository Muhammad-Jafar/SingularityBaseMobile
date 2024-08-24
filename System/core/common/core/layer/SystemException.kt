package core.layer

sealed class SystemException(
    private val throwable: Throwable,
) : Exception(throwable)

data class AuthenticationException(
    val throwable: Throwable,
) : SystemException(throwable)

data class IdentificationException(
    val throwable: Throwable,
) : SystemException(throwable)

data class AccessControlException(
    val throwable: Throwable,
) : SystemException(throwable)
