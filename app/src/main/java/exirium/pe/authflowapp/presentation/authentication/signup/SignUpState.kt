package exirium.pe.authflowapp.presentation.authentication.signup

data class SignUpState(
    val email: String = "",
    val password: String = "",
    val name: String = "",
    val isLoading: Boolean = false,
    val navigateToColors: Boolean = false,
    val toastMessage: String? = null,
)
