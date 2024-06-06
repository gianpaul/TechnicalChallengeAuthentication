package exirium.pe.authflowapp.data.remote.service

import exirium.pe.authflowapp.data.remote.request.LoginRequest
import exirium.pe.authflowapp.data.remote.request.RegisterRequest
import exirium.pe.authflowapp.data.remote.response.ColorResponse
import exirium.pe.authflowapp.data.remote.response.LoginResponse
import exirium.pe.authflowapp.data.remote.response.RegisterResponse
import exirium.pe.authflowapp.data.remote.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ReqresApi {
    @GET("users")
    suspend fun getUsers(@Query("page") page: Int): Response<UserResponse>

    @POST("register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): Response<RegisterResponse>

    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @GET("unknown")
    suspend fun getColors(@Query("page") page: Int): Response<ColorResponse>
}