package core.layer

interface AccessControlLayer {
    fun catchAccessControlException(e: Exception?): Exception?
}
