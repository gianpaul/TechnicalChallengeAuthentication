package exirium.pe.authflowapp.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Color(
    val name : String,
    val year: String,
    val pantone: String,
    val color: String
): Parcelable
