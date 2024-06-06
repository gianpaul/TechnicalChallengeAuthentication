package exirium.pe.authflowapp.presentation.authentication.signup

sealed class SignUpEvent {
    data class OnNameChanged(val name: String) : SignUpEvent()
    data class OnPasswordChanged(val password: String) : SignUpEvent()
    data object OnSignUpClicked : SignUpEvent()
    data object OnToastShown : SignUpEvent()
    data object CleanUp : SignUpEvent()
}