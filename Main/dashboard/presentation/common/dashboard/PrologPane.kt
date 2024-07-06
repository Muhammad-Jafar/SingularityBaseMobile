package dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import core.ui.designsystem.LargePadding
import core.ui.designsystem.component.SExtraLargeSpacing
import core.ui.designsystem.component.SLargeSpacing
import core.ui.designsystem.component.SMediumSpacing
import core.ui.designsystem.component.SParagraphSpacing
import core.ui.designsystem.component.STextBody
import core.ui.designsystem.component.STextHeadline1
import core.ui.designsystem.component.STextHeadline2
import core.ui.designsystem.component.STextLabel
import core.ui.designsystem.component.STextTitle
import org.jetbrains.compose.resources.painterResource
import system.designsystem.resources.Res
import system.designsystem.resources.ahmad_shufyan
import system.designsystem.resources.logo_of_singularity_indonesia
import system.designsystem.resources.logo_of_singularity_indonesia_circle

@Composable
fun PrologPane() {

    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .padding(
                horizontal = LargePadding
            ),
    ) {

        item {
            SExtraLargeSpacing()
            Image(
                modifier = Modifier.size(90.dp),
                painter = painterResource(Res.drawable.logo_of_singularity_indonesia),
                contentDescription = "Singularity Indonesia"
            )
            STextTitle("Singularity Indonesia Present")
            STextBody(
                text = "This app is for educational purpose only, to demonstrate Singularity Indonesia's Multiplatform Codebase Kotlin. The source code of this app is free to use, and distributed freely under Creative Common license — Under the name of Singularity Indonesia.",
            )
            SParagraphSpacing()
            STextBody(
                text = "Follow the link bellow to download the source code of this app."
            )
            STextBody(
                "https://github.com/SingularityIndonesia/SingularityBaseMobile/",
                color = MaterialTheme.colorScheme.tertiary,
            )
            SParagraphSpacing()
            STextHeadline1(
                text = "Team and Contributors"
            )
            STextHeadline2(
                text = "Project Manager"
            )
            Avatar(
                painter = painterResource(Res.drawable.logo_of_singularity_indonesia_circle),
                name = "Stefanus Ayudha",
                linkedInID = "stefanus-ayudha-447a98b5",
                email = "stefanus.ayudha@gmail.com"
            )
            STextHeadline2(
                text = "System designer"
            )
            Avatar(
                painter = painterResource(Res.drawable.ahmad_shufyan),
                name = "Ahmad Shufyan",
                linkedInID = "ahmad-shufyan-319639200",
                email = "shufyan32@gmail.com"
            )

            SExtraLargeSpacing()
            SExtraLargeSpacing()
            SExtraLargeSpacing()
            SExtraLargeSpacing()
        }
    }
}

@Composable
fun Avatar(
    painter: Painter,
    name: String,
    linkedInID: String,
    email: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier.size(90.dp),
            shape = CircleShape,
        ) {
            Image(
                painter = painter,
                contentScale = ContentScale.Fit,
                contentDescription = name
            )
        }
        SLargeSpacing()
        Column {
            STextBody(
                text = name
            )
            SMediumSpacing()
            Row {
                STextLabel("Linkedin:")
                SMediumSpacing()
                STextLabel(
                    text = linkedInID,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
            Row {
                STextLabel("Email:")
                SMediumSpacing()
                STextLabel(
                    text = email,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }

}