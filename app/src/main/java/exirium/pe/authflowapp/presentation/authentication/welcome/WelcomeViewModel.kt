package exirium.pe.authflowapp.presentation.authentication.welcome

import android.util.Patterns.EMAIL_ADDRESS
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import exirium.pe.authflowapp.core.presentation.BaseViewModel
import exirium.pe.authflowapp.domain.repository.AuthenticationRepository
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    savedStateHandle: SavedStateHandle? = null
) : BaseViewModel<WelcomeState, WelcomeEvent>(savedStateHandle) {

    override fun initialState(savedStateHandle: SavedStateHandle?): WelcomeState {
        return WelcomeState()
    }

    override fun onEvent(welcomeEvent: WelcomeEvent) {
        when (welcomeEvent) {
            WelcomeEvent.OnContinueClicked -> validateEmail()
            is WelcomeEvent.OnEmailChanged -> setState { copy(email = welcomeEvent.email) }
            WelcomeEvent.OnToastShown -> setState { copy(toastMessage = null) }
            WelcomeEvent.CleanData -> setState { WelcomeState() }
        }
    }

    private fun validateEmail() {
        val isValidateEmail = EMAIL_ADDRESS.matcher(state.value.email).matches()
        if (isValidateEmail) {
            checkEmailExists()
        } else {
            setState { copy(emailValidated = false, toastMessage = "This is not a valid email") }
        }
    }

    private fun checkEmailExists() {
        execute(
            onStart = { state.value.copy(isLoading = true) },
            onComplete = { currentState ->
                val user = currentState.user
                if (user != null) {
                    currentState.copy(
                        emailExists = true,
                        emailValidated = false,
                        isLoading = false
                    )
                } else {
                    currentState.copy(emailExists = false, emailValidated = true, isLoading = false)
                }
            },
            onError = { currentState, exception ->
                currentState.copy(
                    toastMessage = exception.message,
                    emailExists = false,
                    emailValidated = false,
                    isLoading = false
                )
            },
            block = { currentState ->
                val user = authenticationRepository.fetchEmail(currentState.email)
                currentState.copy(user = user)
            }
        )
    }
}