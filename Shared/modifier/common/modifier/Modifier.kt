package modifier

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier

infix operator fun Modifier.plus(modifier: Modifier) = then(modifier)

val FillMaxSize get() = Modifier.fillMaxSize()
