package exirium.pe.authflowapp.presentation.authentication.welcome

sealed class WelcomeEvent {
    data object OnContinueClicked: WelcomeEvent()
    data class OnEmailChanged(val email: String): WelcomeEvent()
    data object OnToastShown : WelcomeEvent()
    data object CleanData : WelcomeEvent()
}