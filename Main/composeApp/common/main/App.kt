/*
 * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */
package main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import common.StateSaver
import common.getPlatform
import common.isIOS
import core.layer.AccessControlException
import core.layer.AuthenticationException
import core.layer.IdentificationException
import core.ui.SingularityApp
import main.modules.ProtocolLayer
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    val stateSaver = remember { StateSaver() }
    val context = remember { MainContext() }
    val protocolLayers =
        remember {
            ProtocolLayer { e ->
                when (e) {
                    is AuthenticationException -> navController.popBackStack(route = LoginRoute.ROUTE, inclusive = true)
                    is IdentificationException -> TODO()
                    is AccessControlException -> TODO()
                }
            }
        }

    val topPadding =
        WindowInsets.safeDrawing
            .asPaddingValues()
            .calculateTopPadding()
            .let {
                if (getPlatform().isIOS()) {
                    it.minus(8.dp)
                } else {
                    it.minus(0.dp)
                }
            }

    SingularityApp {
        Box(
            modifier =
                Modifier
                    .padding(top = topPadding)
                    .imePadding(),
        ) {
            with(context) {
                MainNavigation(
                    navController = navController,
                    stateSaver = stateSaver,
                )
            }
        }
    }
}
