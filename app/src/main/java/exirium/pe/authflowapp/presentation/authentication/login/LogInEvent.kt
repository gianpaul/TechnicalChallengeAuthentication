package exirium.pe.authflowapp.presentation.authentication.login

sealed class LogInEvent {
    data class OnPasswordChanged(val password: String) : LogInEvent()
    data object OnLogInClicked : LogInEvent()
    data object OnToastShown : LogInEvent()
    data object CleanUp : LogInEvent()
}