package exirium.pe.authflowapp.core.data

import retrofit2.Response

/**
 * `ApiUtils` is a utility object that provides a function for making safe API calls.
 * It includes error handling and response transformation.
 */
object ApiUtils {

    suspend fun <T, R> safeApiCall(
        call: suspend () -> Response<T>,
        transform: (T) -> R,
        exception: Exception
    ): R & Any {
        val response = call()
        if (!response.isSuccessful) {
            throw exception
        }
        return response.body()?.let(transform)
            ?: throw exception
    }
}