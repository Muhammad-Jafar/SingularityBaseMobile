package core.layer

interface AuthenticationLayer {
    fun catchAuthenticationException(e: Exception?): Exception?
}
