package exirium.pe.authflowapp.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class User(
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
): Parcelable
