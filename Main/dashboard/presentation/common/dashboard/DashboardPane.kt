package dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import common.StateSaver
import core.ui.SingularityScope
import core.ui.designsystem.ExtraLargePadding
import core.ui.designsystem.LargePadding
import core.ui.designsystem.component.SFloatingActionButton
import core.ui.designsystem.component.STab
import core.ui.designsystem.component.STabRow
import core.ui.designsystem.component.STextLabel
import example.model.TodoID
import org.jetbrains.compose.resources.painterResource
import system.designsystem.resources.Res
import system.designsystem.resources.groot

context(SingularityScope, Context)
@Composable
fun DashboardPane(
    gotoGroot: () -> Unit = {},
    gotoTodoDetail: (TodoID) -> Unit = {},
) {

    val tabs = listOf("Singularity", "Todo List")
    val tabNavController = rememberNavController()

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    LaunchedEffect(Unit) {
        tabNavController.addOnDestinationChangedListener { _, destination, _ ->
            selectedTabIndex =
                if (destination.route?.contains("todo") == true)
                    1
                else 0
        }
    }
    val stateSaver = remember {
        StateSaver()
    }

    Box(
        modifier = Modifier
            .imePadding()
    ) {

        Column {
            STabRow(
                selectedTabIndex = selectedTabIndex,
            ) {
                tabs.mapIndexed { index, title ->
                    STab(
                        modifier = Modifier.height(60.dp),
                        selected = index == selectedTabIndex,
                        onClick = {
                            when (index) {
                                0 -> tabNavController.navigate("prolog")
                                1 -> tabNavController.navigate("todo-list")
                            }
                        },
                    ) {
                        STextLabel(title)
                    }
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth()
                    .weight(1f),
            ) {
                DashboardTabNavigation(
                    navController = tabNavController,
                    stateSaver = stateSaver,
                    goToTodoDetail = gotoTodoDetail
                )
            }
        }

        SFloatingActionButton(
            modifier = Modifier.align(Alignment.BottomEnd)
                .padding(
                    vertical = ExtraLargePadding + LargePadding,
                    horizontal = LargePadding
                ),
            onClick = {
                gotoGroot.invoke()
            }
        ) {
            Image(
                modifier = Modifier.size(30.dp),
                painter = painterResource(Res.drawable.groot),
                contentDescription = "Groot customer service"
            )
        }
    }
}
