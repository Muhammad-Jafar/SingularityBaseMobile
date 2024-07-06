/*
 * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */
package core.ui.designsystem.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun SSmallSpacing() {
    Spacer(
        modifier = Modifier.size(core.ui.designsystem.SmallSpacing)
    )
}

@Composable
fun SMediumSpacing() {
    Spacer(
        modifier = Modifier.size(core.ui.designsystem.MediumSpacing)
    )
}

@Composable
fun SLargeSpacing() {
    Spacer(
        modifier = Modifier.size(core.ui.designsystem.LargeSpacing)
    )
}

@Composable
fun SExtraLargeSpacing() {
    Spacer(
        modifier = Modifier.size(core.ui.designsystem.ExtraLargeSpacing)
    )
}

@Composable
fun SParagraphSpacing() {
    Spacer(
        modifier = Modifier.size(core.ui.designsystem.ParagraphSpacing)
    )
}

@Composable
fun ColumnScope.Expand() {
    Spacer(
        modifier = Modifier.weight(1f)
    )
}

@Composable
fun RowScope.Expand() {
    Spacer(
        modifier = Modifier.weight(1f)
    )
}