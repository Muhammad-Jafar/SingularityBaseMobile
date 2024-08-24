package authentication

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import core.ui.SingularityScope
import core.ui.designsystem.component.Expand
import core.ui.designsystem.component.SExtraLargeIcon
import core.ui.designsystem.component.SExtraLargeSpacing
import core.ui.designsystem.component.SLargeSpacing
import core.ui.designsystem.component.SPrimaryButton
import core.ui.designsystem.component.STextLabel
import modifier.FillMaxSize
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import system.designsystem.resources.Res
import system.designsystem.resources.logo_of_singularity_indonesia

context(SingularityScope, Context)
@Composable
fun LoginPane(onLoginSuccess: () -> Unit) {
    Column(
        modifier = FillMaxSize,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Expand()
        SExtraLargeIcon(
            painter = painterResource(Res.drawable.logo_of_singularity_indonesia),
            contentDescription = null,
        )
        SLargeSpacing()
        SPrimaryButton(
            onClick = onLoginSuccess,
        ) {
            STextLabel("Login to Singularity")
        }
        Expand()
        SExtraLargeSpacing()
    }
}

@Preview
@Composable
private fun Preview() {
}
