/*
 * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */
package main

import LoginRoute
import ai_chat.pane.aichat.AIChatPane
import ai_chat.pane.aichat.AIChatPanePld
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import authentication.LoginPane
import common.StateSaver
import core.ui.SingularityScope
import dashboard.DashboardRoute
import dashboard.pane.dashboard.DashboardPane
import todolist.pane.tododetail.TodoDetailPane
import todolist.pane.tododetail.TodoDetailPanePld

context(SingularityScope, MainContext)
@Composable
fun MainNavigation(
    navController: NavHostController,
    stateSaver: StateSaver,
) {
    NavHost(
        navController = navController,
        startDestination = LoginRoute.ROUTE,
    ) {
        composable(LoginRoute.ROUTE) {
            with(authenticationContext) {
                LoginPane(
                    onLoginSuccess = {
                        val destination = DashboardRoute()
                        navController.navigate(destination.route())
                    },
                )
            }
        }

        composable(
            route = DashboardRoute.ROUTE,
        ) {
            with(dashboardContext) {
                DashboardPane(
                    gotoGroot = {
                        navController.navigate("groot")
                    },
                    gotoTodoDetail = {
                        navController.navigate("todo-detail/${it.value}")
                    },
                )
            }
        }

        composable(
            route = "groot",
        ) {
            val payload =
                remember {
                    AIChatPanePld()
                }

            with(dashboardContext.aiChatContext) {
                AIChatPane(
                    pld = payload,
                    stateSaver = stateSaver,
                    onBack = {
                        navController.popBackStack()
                    },
                )
            }
        }

        composable(
            route = "todo-detail/{todoID}",
            arguments =
                listOf(
                    navArgument("todoID") {
                        type = NavType.StringType
                    },
                ),
        ) { backstackEntry ->

            val userID =
                backstackEntry.arguments
                    ?.getString("todoID")
                    ?: throw Error("I'm too lazy to handle error.")

            val payload =
                remember(userID) {
                    TodoDetailPanePld(
                        id = userID,
                        onBack = {
                            navController.popBackStack()
                        },
                    )
                }

            with(todoListContext) {
                TodoDetailPane(
                    pld = payload,
                    stateSaver = stateSaver,
                )
            }
        }
    }
}
