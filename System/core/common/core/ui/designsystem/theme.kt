/*
 * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */
package core.ui.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember


@Composable
fun SingularityTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = remember("$darkTheme") {
        if (darkTheme)
            MaterialDarkColorSchemeBinder
        else
            MaterialLightColorSchemeBinder
    }

    @Suppress("DEPRECATION")
    val systemToken = remember { SystemToken() }

    CompositionLocalProvider(
        DesignToken provides systemToken,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = MaterialTypographyBinder,
            content = content
        )
    }
}

object SingularityTheme {

    @Suppress("DEPRECATION")
    val attr: SystemToken
        @Composable
        get() = DesignToken.current
}