package exirium.pe.authflowapp.presentation.color.colors

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import exirium.pe.authflowapp.core.presentation.BaseViewModel
import exirium.pe.authflowapp.domain.repository.ReqresRepository
import javax.inject.Inject

@HiltViewModel
class ColorListViewModel @Inject constructor(
    private val reqresRepository: ReqresRepository,
    savedStateHandle: SavedStateHandle? = null
) : BaseViewModel<ColorListState, ColorListEvent>(savedStateHandle) {

    override fun initialState(savedStateHandle: SavedStateHandle?): ColorListState {
        return ColorListState()
    }

    init {
        onEvent(ColorListEvent.LoadColors)
    }

    override fun onEvent(event: ColorListEvent) {
        when (event) {
            ColorListEvent.LoadColors -> loadColors()
            ColorListEvent.LoadMore -> loadMoreColors()
        }
    }

    private fun loadColors() {
        execute(
            onStart = { state.value.copy(isLoading = true) },
            onComplete = { it.copy(isLoading = false) },
            onError = { currentState, _ -> currentState.copy(isLoading = false) },
            block = { currentState ->
                val colors = reqresRepository.getColors(currentState.page)
                currentState.copy(
                    colors = currentState.colors + colors,
                    page = currentState.page + 1,
                    hasMore = colors.isNotEmpty()
                )
            }
        )
    }

    private fun loadMoreColors() {
        if (!state.value.isLoading && state.value.hasMore) {
            loadColors()
        }
    }
}