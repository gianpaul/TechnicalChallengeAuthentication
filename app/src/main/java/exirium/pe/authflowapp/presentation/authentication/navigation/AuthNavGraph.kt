package exirium.pe.authflowapp.presentation.authentication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import exirium.pe.authflowapp.presentation.authentication.login.LogInScreen
import exirium.pe.authflowapp.presentation.authentication.signup.SignUpScreen
import exirium.pe.authflowapp.presentation.authentication.welcome.WelcomeScreen
import exirium.pe.authflowapp.presentation.color.colors.ColorListScreen

@Composable
fun AuthNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Route.Authentication.route
    ) {
        composable(Route.Authentication.route) {
            WelcomeScreen(navController = navController)
        }

        composable(
            route = Route.LogIn.route,
            arguments = listOf(navArgument("user") { type = NavType.StringType })
        ) {
            LogInScreen(navController = navController )
        }

        composable(
            route = Route.SignUp.route,
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) {
            SignUpScreen(navController = navController)
        }

        composable(
            route = Route.Colors.route
        ) {
            ColorListScreen()
        }
    }
}