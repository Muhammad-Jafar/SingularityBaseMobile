package core.util.systemlayer

import androidx.compose.runtime.Immutable

@Immutable
interface GestureDetectorScope {
    fun notifyGestureDetector()
}

@Immutable
class GestureDetectorScopeImpl : GestureDetectorScope {
    override fun notifyGestureDetector() {
        TODO("Not yet implemented")
    }
}