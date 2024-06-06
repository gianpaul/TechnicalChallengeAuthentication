package exirium.pe.authflowapp.data.mapper

import exirium.pe.authflowapp.data.remote.response.ColorData
import exirium.pe.authflowapp.domain.entity.Color

fun ColorData.asEntity () = Color(
    name = name,
    year = year.toString(),
    color = color,
    pantone = pantoneValue
)