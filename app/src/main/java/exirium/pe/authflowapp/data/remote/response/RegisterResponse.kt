package exirium.pe.authflowapp.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    @SerialName("id") val id: Int,
    @SerialName("token") val token: String
)
