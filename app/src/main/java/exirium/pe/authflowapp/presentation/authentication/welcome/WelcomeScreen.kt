package exirium.pe.authflowapp.presentation.authentication.welcome

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import exirium.pe.authflowapp.R
import exirium.pe.authflowapp.presentation.authentication.navigation.Route
import exirium.pe.authflowapp.ui.components.BlurredCard
import exirium.pe.authflowapp.ui.components.ClickableTextComponent
import exirium.pe.authflowapp.ui.components.CustomButton
import exirium.pe.authflowapp.ui.components.PrimaryButton
import exirium.pe.authflowapp.ui.components.TextInput

@Composable
fun WelcomeScreen(
    navController: NavHostController,
    viewModel: WelcomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    HandleNavigation(navController = navController, state = state, viewModel = viewModel)
    DisplayToastMessages(context = context, state = state, viewModel = viewModel)

    WelcomeContent(state = state) { viewModel.onEvent(it) }
}

@Composable
private fun HandleNavigation(
    navController: NavHostController,
    state: WelcomeState,
    viewModel: WelcomeViewModel
) {
    LaunchedEffect(state) {
        when {
            state.emailExists -> {
                state.user?.let { user ->
                    navController.navigate(Route.LogIn.createRoute(user))
                    viewModel.onEvent(WelcomeEvent.CleanData)
                }
            }
            state.emailValidated -> {
                navController.navigate(Route.SignUp.createRoute(state.email))
                viewModel.onEvent(WelcomeEvent.CleanData)
            }
        }
    }
}

@Composable
private fun DisplayToastMessages(
    context: Context,
    state: WelcomeState,
    viewModel: WelcomeViewModel
) {
    state.toastMessage?.let { message ->
        LaunchedEffect(message) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            viewModel.onEvent(WelcomeEvent.OnToastShown)
        }
    }
}

@Composable
private fun WelcomeContent(
    state: WelcomeState,
    onEvent: (WelcomeEvent) -> Unit
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
                text = "Hi!",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.padding(8.dp))
            WelcomeCard(state, onEvent)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun WelcomeCard(
    state: WelcomeState,
    onEvent: (WelcomeEvent) -> Unit
) {
    BlurredCard {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            RegisterSection(state, onEvent)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.light_green),
                text = "or"
            )
            Spacer(modifier = Modifier.height(8.dp))
            SocialLoginButtons()
            Spacer(modifier = Modifier.height(16.dp))
            BottomDescription()
        }
    }
}

@Composable
private fun RegisterSection(
    state: WelcomeState,
    onEvent: (WelcomeEvent) -> Unit
) {
    TextInput(
        value = state.email,
        onValueChange = { onEvent(WelcomeEvent.OnEmailChanged(it)) },
        label = "Email",
        keyboardType = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
    Spacer(modifier = Modifier.height(8.dp))
    PrimaryButton(
        text = "Continue",
        isLoading = state.isLoading,
        onClick = { onEvent(WelcomeEvent.OnContinueClicked) }
    )
}

@Composable
private fun SocialLoginButtons() {
    CustomButton(
        text = "Continue with Facebook",
        onClick = { /* Handle login */ },
        iconResId = R.drawable.ic_facebook
    )
    Spacer(modifier = Modifier.height(8.dp))
    CustomButton(
        text = "Continue with Google",
        onClick = { /* Handle login */ },
        iconResId = R.drawable.ic_google
    )
    Spacer(modifier = Modifier.height(8.dp))
    CustomButton(
        text = "Continue with Apple",
        onClick = { /* Handle login */ },
        iconResId = R.drawable.ic_apple
    )
}

@Composable
private fun BottomDescription() {
    ClickableTextComponent(
        normalText = "Don't have an account? ",
        boldText = "Sign in",
        onBoldTextClick = { /* Handle sign in */ }
    )
    Spacer(modifier = Modifier.height(16.dp))
    ClickableTextComponent(
        boldText = "Forgot your password? ",
        onBoldTextClick = { /* Handle forgot password */ }
    )
}

@Preview(showSystemUi = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeContent(WelcomeState()) {}
}