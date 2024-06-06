package exirium.pe.authflowapp.data.mapper

import exirium.pe.authflowapp.data.remote.response.UserDataResponse
import exirium.pe.authflowapp.domain.entity.User

fun UserDataResponse.asEntity(): User {
    return User(
        email = this.email,
        firstName = this.firstName,
        lastName = this.lastName,
        avatar = this.avatar
    )
}