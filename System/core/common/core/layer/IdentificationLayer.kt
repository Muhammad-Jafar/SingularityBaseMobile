package core.layer

interface IdentificationLayer  {
    fun catchIdentificationException(e: Exception?): Exception?
}
