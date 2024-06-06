package exirium.pe.authflowapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavHostController
import exirium.pe.authflowapp.R

/**
 * This is the main theme for the AuthFlowApp.
 * It provides a scaffold with a top bar and a content area.
 * The top bar includes a back navigation button.
 * The content area is provided by the caller.
 *
 * @param content A composable function that will be used to fill the content area of the scaffold.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthFlowAppTheme(
    navController: NavHostController,
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        HalfBackgroundContent()
        Scaffold(topBar = {
            TopAppBar(title = { /* Add title here if needed */ }, navigationIcon = {
                IconButton(onClick = { navController.popBackStack()}) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }, colors = topAppBarColors(
                containerColor = Color.Transparent))
        }, content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                content()
            }
        }, containerColor = Color.Transparent
        )
    }
}

/**
 * This composable function provides a half background content.
 * It fills the maximum size and sets the background color.
 * It contains a column with an image and a box.
 */
@Composable
fun HalfBackgroundContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background_color))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row {
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(3f),
                    contentScale = ContentScale.FillWidth
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}