package exirium.pe.authflowapp.presentation.authentication.login

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import exirium.pe.authflowapp.core.presentation.BaseViewModel
import exirium.pe.authflowapp.domain.entity.User
import exirium.pe.authflowapp.domain.repository.AuthenticationRepository
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    savedStateHandle: SavedStateHandle? = null
) : BaseViewModel<LogInState, LogInEvent>(savedStateHandle) {
    override fun initialState(savedStateHandle: SavedStateHandle?): LogInState {
        val userJson = savedStateHandle?.get<String>("user")
        val user = userJson?.let { Json.decodeFromString<User>(it) }
        return user?.let {
            LogInState(
                email = it.email,
                name = "${it.firstName} ${it.lastName}",
                avatarURL = it.avatar
            )
        } ?: LogInState()
    }

    override fun onEvent(event: LogInEvent) {
        when (event) {
            is LogInEvent.OnPasswordChanged -> setState { copy(password = event.password) }
            LogInEvent.OnLogInClicked -> logIn()
            LogInEvent.OnToastShown -> setState { copy(toastMessage = null) }
            LogInEvent.CleanUp -> setState { LogInState() }
        }
    }

    private fun logIn() {
        execute(
            onStart = { state.value.copy(isLoading = true) },
            onComplete = { currentState ->
                currentState.copy(
                    isLoading = false,
                    navigateToColors = true
                )
            },
            onError = { currentState, exception ->
                currentState.copy(
                    toastMessage = exception.message,
                    isLoading = false
                )
            },
            block = { currentState ->
                authenticationRepository.loginUser(currentState.email, currentState.password)
                currentState
            }
        )
    }
}