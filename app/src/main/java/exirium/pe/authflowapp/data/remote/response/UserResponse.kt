package exirium.pe.authflowapp.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse (
    @SerialName("page") val id: Int,
    @SerialName("per_page") val page: Int,
    @SerialName("total") val total: Int,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("data") val data: List<UserDataResponse>
)

@Serializable
data class UserDataResponse (
    @SerialName("id") val id: Int,
    @SerialName("email") val email: String,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("avatar") val avatar: String
)