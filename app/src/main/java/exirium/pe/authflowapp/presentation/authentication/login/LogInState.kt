package exirium.pe.authflowapp.presentation.authentication.login

data class LogInState(
    val avatarURL: String = "",
    val email: String = "",
    val name: String = "",
    val password: String = "",
    val toastMessage: String? = null,
    val navigateToColors: Boolean = false,
    val isLoading: Boolean = false
)
