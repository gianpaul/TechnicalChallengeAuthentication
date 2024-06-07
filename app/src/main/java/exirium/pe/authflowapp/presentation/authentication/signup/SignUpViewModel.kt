package exirium.pe.authflowapp.presentation.authentication.signup

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import exirium.pe.authflowapp.core.presentation.BaseViewModel
import exirium.pe.authflowapp.domain.repository.AuthenticationRepository
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    savedStateHandle: SavedStateHandle? = null
) : BaseViewModel<SignUpState, SignUpEvent>(savedStateHandle) {
    override fun initialState(savedStateHandle: SavedStateHandle?): SignUpState {
        val email = savedStateHandle?.get<String>("email") ?: ""
        return SignUpState(email = email)
    }

    override fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.OnNameChanged -> setState { copy(name = event.name) }
            is SignUpEvent.OnPasswordChanged -> setState { copy(password = event.password) }
            is SignUpEvent.OnSignUpClicked -> signUp()
            SignUpEvent.OnToastShown -> setState { copy(toastMessage = null) }
            SignUpEvent.CleanUp -> setState { SignUpState() }
        }
    }

    private fun signUp() {
        execute(
            onStart = { state.value.copy(isLoading = true) },
            onComplete = { it.copy(isLoading = false, navigateToColors = true) },
            onError = { currentState, exception ->
                currentState.copy(
                    toastMessage = exception.message,
                    isLoading = false
                )
            },
            block = { currentState ->
                authenticationRepository.registerUser(currentState.email, currentState.password)
                currentState
            }
        )
    }
}