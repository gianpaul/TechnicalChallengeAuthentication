package exirium.pe.authflowapp.presentation.color.colors

sealed class ColorListEvent {
    data object LoadColors : ColorListEvent()
    data object LoadMore : ColorListEvent()
}