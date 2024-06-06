package exirium.pe.authflowapp.presentation.authentication.navigation

import android.net.Uri
import exirium.pe.authflowapp.domain.entity.User
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

sealed class Route(val route: String) {
    data object Authentication : Route("authentication")
    data object LogIn : Route("login?user={user}") {
        fun createRoute(user: User): String {
            val userJson = Json.encodeToString(user)
            return "login?user=${Uri.encode(userJson)}"
        }
    }
    data object SignUp : Route("signup?email={email}") {
        fun createRoute(email: String): String {
            return "signup?email=${Uri.encode(email)}"
        }
    }
    data object Colors : Route("colors")
}