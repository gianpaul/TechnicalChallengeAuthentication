package exirium.pe.authflowapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * A Composable function that creates a blurred card.
 * The card has a dark gray background with 50% opacity and rounded corners.
 * The card's content is provided as a Composable function.
 *
 * @param content The content of the card. This is a Composable function.
 */
@Composable
fun BlurredCard(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .background(
                color = Color.DarkGray.copy(alpha = 0.5f), shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        content()
    }
}