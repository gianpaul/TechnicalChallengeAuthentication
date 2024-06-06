package exirium.pe.authflowapp.presentation.authentication.signup

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import exirium.pe.authflowapp.R
import exirium.pe.authflowapp.presentation.authentication.navigation.Route
import exirium.pe.authflowapp.ui.components.BlurredCard
import exirium.pe.authflowapp.ui.components.ClickableTextComponent
import exirium.pe.authflowapp.ui.components.PasswordInput
import exirium.pe.authflowapp.ui.components.PrimaryButton
import exirium.pe.authflowapp.ui.components.TextInput

@Composable
fun SignUpScreen(
    navController: NavHostController,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    HandleSignUpNavigation(navController, state, viewModel)
    DisplaySignUpToastMessages(context, state, viewModel)

    SignUpContent(state) { viewModel.onEvent(it) }
}

@Composable
private fun HandleSignUpNavigation(
    navController: NavHostController,
    state: SignUpState,
    viewModel: SignUpViewModel
) {
    LaunchedEffect(state.navigateToColors) {
        if (state.navigateToColors) {
            navController.navigate(Route.Colors.route) {
                popUpTo(Route.Authentication.route) { inclusive = true }
            }
            viewModel.onEvent(SignUpEvent.CleanUp)
        }
    }
}

@Composable
private fun DisplaySignUpToastMessages(
    context: Context,
    state: SignUpState,
    viewModel: SignUpViewModel
) {
    state.toastMessage?.let { message ->
        LaunchedEffect(message) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            viewModel.onEvent(SignUpEvent.OnToastShown)
        }
    }
}

@Composable
private fun SignUpContent(
    state: SignUpState,
    onEvent: (SignUpEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        Spacer(modifier = Modifier.weight(0.1f))
        Column(modifier = Modifier.weight(0.4f)) {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = "Log In!",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.padding(8.dp))
            SignUpCard(state, onEvent)
        }
    }
}

@Composable
private fun SignUpCard(
    state: SignUpState,
    onEvent: (SignUpEvent) -> Unit
) {
    BlurredCard {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            SignUpInfoText(state)
            Spacer(modifier = Modifier.height(12.dp))
            SignUpForm(state, onEvent)
            Spacer(modifier = Modifier.height(12.dp))
            AgreeAndContinueText()
            Spacer(modifier = Modifier.height(12.dp))
            PrimaryButton(
                text = "Agree and continue",
                isLoading = state.isLoading,
                onClick = { onEvent(SignUpEvent.OnSignUpClicked) }
            )
        }
    }
}

@Composable
private fun SignUpInfoText(state: SignUpState) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        color = colorResource(id = R.color.light_green),
        style = MaterialTheme.typography.bodyMedium,
        text = "Looks like you don't have an account."
    )
    Text(
        modifier = Modifier.fillMaxWidth(),
        color = colorResource(id = R.color.light_green),
        style = MaterialTheme.typography.bodyMedium,
        text = "Let's create a new account for"
    )
    Text(
        modifier = Modifier.fillMaxWidth(),
        color = colorResource(id = R.color.light_green),
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.bodyMedium,
        text = state.email
    )
}

@Composable
private fun SignUpForm(state: SignUpState, onEvent: (SignUpEvent) -> Unit) {
    TextInput(
        value = state.name,
        onValueChange = { onEvent(SignUpEvent.OnNameChanged(it)) },
        label = "Name"
    )
    Spacer(modifier = Modifier.height(8.dp))
    PasswordInput(
        label = "Password",
        value = state.password,
        onValueChange = { onEvent(SignUpEvent.OnPasswordChanged(it)) }
    )
}

@Composable
private fun AgreeAndContinueText() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        color = colorResource(id = R.color.light_green),
        style = MaterialTheme.typography.bodyMedium,
        text = "By selecting Agree and continue below,"
    )
    ClickableTextComponent(
        normalText = "I agree to ",
        boldText = "Terms of Service and Privacy Policy",
        onBoldTextClick = { /* Handle terms and privacy policy click */ }
    )
}

@Preview(showSystemUi = true)
@Composable
fun PreviewSignUpScreen() {
    SignUpContent(SignUpState()) {}
}
