package exirium.pe.authflowapp.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ColorResponse(
    @SerialName("page") val page: Int,
    @SerialName("per_page") val perPage: Int,
    @SerialName("total") val total: Int,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("data") val data: List<ColorData>,
    @SerialName("support") val support: Support
)

@Serializable
data class ColorData(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("year") val year: Int,
    @SerialName("color") val color: String,
    @SerialName("pantone_value") val pantoneValue: String
)

@Serializable
data class Support(
    @SerialName("url") val url: String,
    @SerialName("text") val text: String
)