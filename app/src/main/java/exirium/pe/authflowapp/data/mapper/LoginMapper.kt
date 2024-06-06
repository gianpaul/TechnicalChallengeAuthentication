package exirium.pe.authflowapp.data.mapper

import exirium.pe.authflowapp.data.remote.response.LoginResponse
import exirium.pe.authflowapp.domain.entity.Token

fun LoginResponse.asEntity(): Token {
    return Token(
        token = this.token
    )
}