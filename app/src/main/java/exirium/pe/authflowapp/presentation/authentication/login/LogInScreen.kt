package exirium.pe.authflowapp.presentation.authentication.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import exirium.pe.authflowapp.presentation.authentication.navigation.Route
import exirium.pe.authflowapp.ui.components.BlurredCard
import exirium.pe.authflowapp.ui.components.ClickableTextComponent
import exirium.pe.authflowapp.ui.components.PasswordInput
import exirium.pe.authflowapp.ui.components.PrimaryButton

@Composable
fun LogInScreen(
    navController: NavController, viewModel: LogInViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    HandleLogInNavigation(navController, state, viewModel)
    LogInContent(state) { viewModel.onEvent(it) }
}

@Composable
private fun HandleLogInNavigation(
    navController: NavController, state: LogInState, viewModel: LogInViewModel
) {
    LaunchedEffect(state.navigateToColors) {
        if (state.navigateToColors) {
            navController.navigate(Route.Colors.route) {
                popUpTo(Route.Authentication.route) { inclusive = true }
            }
            viewModel.onEvent(LogInEvent.CleanUp)
        }
    }
}

@Composable
private fun LogInContent(
    state: LogInState, onEvent: (LogInEvent) -> Unit
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
            LogInCard(state, onEvent)
        }
    }
}

@Composable
private fun LogInCard(
    state: LogInState, onEvent: (LogInEvent) -> Unit
) {
    BlurredCard {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            UserInfoRow(state)
            PasswordInput(label = "Password",
                value = state.password,
                onValueChange = { onEvent(LogInEvent.OnPasswordChanged(it)) })
            Spacer(modifier = Modifier.height(8.dp))
            PrimaryButton(
                text = "Continue",
                onClick = { onEvent(LogInEvent.OnLogInClicked) },
                isLoading = state.isLoading
            )
            Spacer(modifier = Modifier.height(8.dp))
            ClickableTextComponent(boldText = "Forgot your password?",
                onBoldTextClick = { /* Handle forgot password */ })
        }
    }
}

@Composable
private fun UserInfoRow(state: LogInState) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(state.avatarURL),
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = state.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
            Text(
                text = state.email, style = MaterialTheme.typography.bodyMedium, color = Color.White
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LogInScreenPreview() {
    LogInContent(state = LogInState(), onEvent = {})
}
