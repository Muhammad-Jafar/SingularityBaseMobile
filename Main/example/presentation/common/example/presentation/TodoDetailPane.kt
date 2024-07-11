/*
 * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */
package example.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import common.StateSaver
import core.ui.SingularityScope
import core.ui.designsystem.LargePadding
import core.ui.designsystem.component.STextBody
import core.ui.designsystem.component.STextHeadline1
import core.ui.designsystem.component.STextHeadline2
import core.ui.designsystem.component.STextHeadline3
import core.ui.designsystem.component.STextSubTitle
import core.ui.designsystem.component.STopAppBar
import example.model.Context

@Immutable
data class TodoDetailPanePld(
    val id: String,
    val onBack: () -> Unit
)

context(SingularityScope, Context)
@Composable
fun TodoDetailPane(
    pld: TodoDetailPanePld,
    stateSaver: StateSaver
) {
    Column {
        STopAppBar(
            title = "Todo List Title",
            onBack = pld.onBack
        )
        STextSubTitle(
            """
                This is detail screen for Todo with id = ${pld.id}.
                I'm too lazy to do it.
                But this is enough to demonstrate navigation.
            """.trimIndent(),
            modifier = Modifier.padding(
                horizontal = LargePadding
            )
        )
        STextHeadline1(
            "Headline 1",
            modifier = Modifier.padding(
                horizontal = LargePadding
            )
        )
        STextBody(
            "This is normal text. aksdnkj aslkd lakndl asl salkdnlak dnslkas dlak dlad landlk asd. askjdnoa dla dlaksd alsd las da.",
            modifier = Modifier.padding(
                horizontal = LargePadding
            )
        )
        STextHeadline2(
            "Headline 2",
            modifier = Modifier.padding(
                horizontal = LargePadding
            )
        )

        STextBody(
            "This is normal text. aksdnkj aslkd lakndl asl salkdnlak dnslkas dlak dlad landlk asd. askjdnoa dla dlaksd alsd las da.",
            modifier = Modifier.padding(
                horizontal = LargePadding
            )
        )
        STextHeadline3(
            "Headline 3",
            modifier = Modifier.padding(
                horizontal = LargePadding
            )
        )
        STextBody(
            "This is normal text. aksdnkj aslkd lakndl asl salkdnlak dnslkas dlak dlad landlk asd. askjdnoa dla dlaksd alsd las da.",
            modifier = Modifier.padding(
                horizontal = LargePadding
            )
        )
    }
}