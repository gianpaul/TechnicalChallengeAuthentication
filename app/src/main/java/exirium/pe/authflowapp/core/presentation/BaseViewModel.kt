package exirium.pe.authflowapp.core.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * BaseViewModel is an abstract class that represents a ViewModel with a state and events.
 * It provides common functionality for managing state and executing tasks.
 *
 * @param State The type of the state that this ViewModel holds.
 * @param Event The type of the events that this ViewModel can handle.
 * @property savedStateHandle An optional SavedStateHandle for saving and restoring state.
 */
abstract class BaseViewModel<State : Any, Event> (
    private val savedStateHandle: SavedStateHandle? = null
): ViewModel() {

    // A private MutableStateFlow that holds the current state.
    private val _state = MutableStateFlow(initialState(savedStateHandle))

    // A public StateFlow that exposes the current state to observers.
    val state: StateFlow<State> = _state.asStateFlow()

    /**
     * This function should return the initial state of the ViewModel.
     * It is called when the ViewModel is created.
     *
     * @param savedStateHandle An optional SavedStateHandle for saving and restoring state.
     * @return The initial state.
     */
    abstract fun initialState(savedStateHandle: SavedStateHandle?): State

    /**
     * This function updates the current state using a reducer function.
     *
     * @param reducer A function that takes the current state and returns a new state.
     */
    protected fun setState(reducer: State.() -> State) {
        _state.update { currentState ->
            currentState.reducer()
        }
    }

    /**
     * This function executes a suspending block of code and updates the state based on the result.
     * It updates the state before starting, after completing, and when an error occurs.
     *
     * @param onStart A function that returns a new state before the block is executed.
     * @param onComplete A function that returns a new state after the block is successfully executed.
     * @param onError A function that takes an exception and returns a new state when an error occurs.
     * @param block The suspending block of code to be executed.
     */
    protected fun execute(
        onStart: () -> State,
        onComplete: (State) -> State,
        onError: (State, Throwable) -> State,
        block: suspend (State) -> State
    ) {
        viewModelScope.launch {
            runCatching {
                _state.update { onStart() }
                block(state.value)
            }.onSuccess { result ->
                _state.update { onComplete(result) }
            }.onFailure { exception ->
                _state.update { onError(state.value, exception) }
            }
        }
    }

    /**
     * This function handles an event.
     * It should be overridden by subclasses to provide specific event handling.
     *
     * @param event The event to handle.
     */
    abstract fun onEvent(event: Event)
}