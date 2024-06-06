package exirium.pe.authflowapp.domain.repository

import exirium.pe.authflowapp.domain.entity.Color
import exirium.pe.authflowapp.domain.entity.Token
import exirium.pe.authflowapp.domain.entity.User

interface ReqresRepository {
    suspend fun getAllUsers(): List<User>
    suspend fun registerUser(email: String, password: String): Token
    suspend fun loginUser(email: String, password: String): Token
    suspend fun getColors(page: Int): List<Color>
}