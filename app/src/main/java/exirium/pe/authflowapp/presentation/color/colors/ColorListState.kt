package exirium.pe.authflowapp.presentation.color.colors

import exirium.pe.authflowapp.domain.entity.Color

data class ColorListState(
    val colors: List<Color> = emptyList(),
    val page: Int = 1,
    val isLoading: Boolean = false,
    val hasMore: Boolean = true
)
