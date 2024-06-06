package exirium.pe.authflowapp.presentation.authentication.welcome

import exirium.pe.authflowapp.domain.entity.User

data class WelcomeState(
    val email: String = "",
    val toastMessage: String? = null,
    val emailExists: Boolean = false,
    val user: User? = null,
    val isLoading: Boolean = false,
    val emailValidated: Boolean = false
)