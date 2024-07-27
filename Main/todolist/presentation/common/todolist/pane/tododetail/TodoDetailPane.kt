/*
 * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */
package todolist.pane.tododetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import common.StateSaver
import core.ui.SingularityScope
import core.ui.designsystem.SingularityTheme
import core.ui.designsystem.component.STextBody
import core.ui.designsystem.component.STextHeadline1
import core.ui.designsystem.component.STextHeadline2
import core.ui.designsystem.component.STextHeadline3
import core.ui.designsystem.component.STextSubTitle
import core.ui.designsystem.component.STopAppBar
import core.ui.designsystem.`large-padding`
import todolist.Context

@Immutable
data class TodoDetailPanePld(
    val id: String,
    val onBack: () -> Unit,
)

context(SingularityScope, Context)
@Composable
fun TodoDetailPane(
    pld: TodoDetailPanePld,
    stateSaver: StateSaver,
) {
    val attr = SingularityTheme.attr
    Column {
        STopAppBar(
            title = "Todo List Title",
            onBack = pld.onBack,
        )
        STextSubTitle(
            """
            This is detail screen for Todo with id = ${pld.id}.
            I'm too lazy to do it.
            But this is enough to demonstrate navigation.
            """.trimIndent(),
            modifier =
                Modifier.padding(
                    horizontal = attr.`large-padding`,
                ),
        )
        STextHeadline1(
            "Headline 1",
            modifier =
                Modifier.padding(
                    horizontal = attr.`large-padding`,
                ),
        )
        STextBody(
            "This is normal text. aksdnkj aslkd lakndl asl salkdnlak dnslkas dlak dlad landlk asd. askjdnoa dla dlaksd alsd las da.",
            modifier =
                Modifier.padding(
                    horizontal = attr.`large-padding`,
                ),
        )
        STextHeadline2(
            "Headline 2",
            modifier =
                Modifier.padding(
                    horizontal = attr.`large-padding`,
                ),
        )

        STextBody(
            "This is normal text. aksdnkj aslkd lakndl asl salkdnlak dnslkas dlak dlad landlk asd. askjdnoa dla dlaksd alsd las da.",
            modifier =
                Modifier.padding(
                    horizontal = attr.`large-padding`,
                ),
        )
        STextHeadline3(
            "Headline 3",
            modifier =
                Modifier.padding(
                    horizontal = attr.`large-padding`,
                ),
        )
        STextBody(
            "This is normal text. aksdnkj aslkd lakndl asl salkdnlak dnslkas dlak dlad landlk asd. askjdnoa dla dlaksd alsd las da.",
            modifier =
                Modifier.padding(
                    horizontal = attr.`large-padding`,
                ),
        )
    }
}
