package exirium.pe.authflowapp.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Token(
    val id : Int? = null,
    val token: String
): Parcelable
