package exirium.pe.authflowapp.data.repository

import exirium.pe.authflowapp.core.data.ApiUtils
import exirium.pe.authflowapp.data.exception.ColorException
import exirium.pe.authflowapp.data.exception.LoginException
import exirium.pe.authflowapp.data.exception.RegisterException
import exirium.pe.authflowapp.data.exception.UsersException
import exirium.pe.authflowapp.data.mapper.asEntity
import exirium.pe.authflowapp.data.remote.request.LoginRequest
import exirium.pe.authflowapp.data.remote.request.RegisterRequest
import exirium.pe.authflowapp.data.remote.service.ReqresApi
import exirium.pe.authflowapp.domain.entity.Color
import exirium.pe.authflowapp.domain.entity.Token
import exirium.pe.authflowapp.domain.entity.User
import exirium.pe.authflowapp.domain.repository.AuthenticationRepository
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val reqresApi: ReqresApi
) : AuthenticationRepository {
    override suspend fun fetchEmail(email: String): User? {
        val users = mutableListOf<User>()
        var currentPage = 1
        var totalPages: Int

        do {
            val userResponse = ApiUtils.safeApiCall(
                call = { reqresApi.getUsers(page = currentPage) },
                transform = { it },
                exception = UsersException("Error getting users")
            )
            users.addAll(userResponse.data.map { it.asEntity() }
                // Filter out the user with emai for test purposes
                .filter { it.email != "eve.holt@reqres.in" })
            totalPages = userResponse.totalPages
            currentPage++
        } while (currentPage <= totalPages)

        return users.find { it.email == email }
    }

    override suspend fun registerUser(email: String, password: String): Token {
        return ApiUtils.safeApiCall(
            call = { reqresApi.register(RegisterRequest(email = email, password = password)) },
            transform = { it.asEntity() },
            exception = RegisterException("Error registering user")
        )
    }

    override suspend fun loginUser(email: String, password: String): Token {
        return ApiUtils.safeApiCall(
            call = { reqresApi.login(LoginRequest(email = email, password = password)) },
            transform = { it.asEntity() },
            exception = LoginException("Error logging in")
        )
    }

    override suspend fun getColors(page: Int): List<Color> {
        return ApiUtils.safeApiCall(
            call = { reqresApi.getColors(page = page) },
            transform = { it.data.map { it.asEntity() } },
            exception = ColorException("Error getting colors")
        )
    }
}