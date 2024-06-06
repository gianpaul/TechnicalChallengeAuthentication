package exirium.pe.authflowapp.data.mapper

import exirium.pe.authflowapp.data.remote.response.RegisterResponse
import exirium.pe.authflowapp.domain.entity.Token

fun RegisterResponse.asEntity(): Token {
    return Token(
        id = this.id,
        token = this.token
    )
}