package exirium.pe.authflowapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import exirium.pe.authflowapp.R
import exirium.pe.authflowapp.presentation.authentication.navigation.AuthNavGraph
import exirium.pe.authflowapp.ui.theme.AuthFlowAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController: NavHostController = rememberNavController()
            AuthFlowAppTheme(navController = navController) {
                AuthNavGraph(navController = navController)
            }
        }
        val backgroundColor = ContextCompat.getColor(this, R.color.background_color)
        window.statusBarColor = backgroundColor
        window.navigationBarColor = backgroundColor
    }
}