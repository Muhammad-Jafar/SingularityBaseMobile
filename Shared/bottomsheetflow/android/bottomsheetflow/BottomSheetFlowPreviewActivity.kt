/*
 * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */
package bottomsheetflow

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import core.ui.SingularityApp
import core.ui.SingularityScope
import core.ui.designsystem.component.SExtraLargeSpacing
import core.ui.designsystem.component.SMediumSpacing
import core.ui.designsystem.component.SPrimaryButton
import core.ui.designsystem.component.SSecondaryButton
import core.ui.designsystem.component.STertiaryButton
import core.ui.designsystem.component.STextTitle
import kotlinx.coroutines.launch
import simpleactivity.SimpleActivity

class BottomSheetFlowPreviewActivity : SimpleActivity() {

    @Composable
    override fun App() {
        var showSheet by remember { mutableStateOf(false) }

        SingularityApp {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
            ) {
                SPrimaryButton(
                    onClick = { showSheet = true },
                    modifier = Modifier.align(Alignment.Center),
                ) {
                    Text(text = "Open Bottom Sheet")
                }
            }

            if (showSheet)
                BottomSheetInput(
                    onCancel = {
                        showSheet = false
                    },
                    onFinish = {
                        showSheet = false
                    }
                )
        }
    }
}

context(SingularityScope)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetInput(
    onCancel: () -> Unit,
    onFinish: () -> Unit
) {
    val scope = rememberCoroutineScope()

    BottomSheetFlow(
        startDestination = "input1",
        onCancel = onCancel
    ) {
        route(
            route = "input1",
            onDismiss = {
                // you can show dialog here
                // showDismissDialog = true
                false
            }
        ) { sheetState ->
            Sheet1(
                onBack = {
                    scope.launch {
                        sheetState.hide()
                        onCancel.invoke()
                    }
                },
                onNext = {
                    scope.launch {
                        sheetState.hide()
                        navigate("input2")
                    }
                },
                onCancel = {
                    scope.launch {
                        sheetState.hide()
                        onCancel.invoke()
                    }
                }
            )
        }

        route(
            "input2",
            onDismiss = {
                // you can show dialog here
                // showDismissDialog = true
                false
            }
        ) { sheetState ->
            Sheet2(
                onBack = {
                    scope.launch {
                        sheetState.hide()
                        popBackStack()
                    }
                },
                onNext = {
                    scope.launch {
                        sheetState.hide()
                        navigate("input3")
                    }
                },
                onCancel = {
                    scope.launch {
                        sheetState.hide()
                        onCancel.invoke()
                    }
                }
            )
        }

        route(
            "input3",
            onDismiss = {
                // you can show dialog here
                // showDismissDialog = true
                false
            }
        ) { sheetState ->
            Sheet3(
                onBack = {
                    scope.launch {
                        sheetState.hide()
                        popBackStack()
                    }
                },
                onFinish = {
                    scope.launch {
                        sheetState.hide()
                        onFinish.invoke()
                    }
                },
                onCancel = {
                    scope.launch {
                        sheetState.hide()
                        onCancel.invoke()
                    }
                }
            )
        }
    }
}

context(SingularityScope)
@Composable
fun Sheet1(
    onBack: () -> Unit,
    onNext: () -> Unit,
    onCancel: () -> Unit
) {
    Column {
        STextTitle(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Input 1"
        )
        SExtraLargeSpacing()
        Row {
            STertiaryButton(
                onClick = onCancel,
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "Cancel")
            }
            SMediumSpacing()
            SSecondaryButton(
                onClick = onBack,
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "Back")
            }
            SMediumSpacing()
            SPrimaryButton(
                modifier = Modifier.weight(1f),
                onClick = onNext
            ) {
                Text(text = "Next")
            }
        }
        SExtraLargeSpacing()
    }
}

context(SingularityScope)
@Composable
fun Sheet2(
    onBack: () -> Unit,
    onNext: () -> Unit,
    onCancel: () -> Unit
) {
    Column {
        STextTitle(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Input 2"
        )
        SExtraLargeSpacing()
        Row(
            modifier = Modifier.align(Alignment.End)
        ) {
            STertiaryButton(
                modifier = Modifier.weight(1f),
                onClick = onCancel
            ) {
                Text(text = "Cancel")
            }
            SMediumSpacing()
            SSecondaryButton(
                modifier = Modifier.weight(1f),
                onClick = onBack
            ) {
                Text(text = "Back")
            }
            SMediumSpacing()
            SPrimaryButton(
                modifier = Modifier.weight(1f),
                onClick = onNext
            ) {
                Text(text = "Next")
            }
        }
        SExtraLargeSpacing()
    }
}

context(SingularityScope)
@Composable
fun Sheet3(
    onBack: () -> Unit,
    onFinish: () -> Unit,
    onCancel: () -> Unit
) {
    Column {
        STextTitle(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Input 1"
        )
        SExtraLargeSpacing()
        Row(
            modifier = Modifier.align(Alignment.End)
        ) {
            STertiaryButton(
                modifier = Modifier.weight(1f),
                onClick = onCancel
            ) {
                Text(text = "Cancel")
            }
            SMediumSpacing()
            SSecondaryButton(
                modifier = Modifier.weight(1f),
                onClick = onBack
            ) {
                Text(text = "Back")
            }
            SMediumSpacing()
            SPrimaryButton(
                modifier = Modifier.weight(1f),
                onClick = onFinish
            ) {
                Text(text = "Finish")
            }
        }
        SExtraLargeSpacing()
    }
}