package exirium.pe.authflowapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import exirium.pe.authflowapp.R

/**
 * A Composable function that creates a clickable text component.
 * The component consists of a normal text and a bold text.
 * The bold text is clickable and triggers a provided function when clicked.
 *
 * @param normalText The normal text to be displayed. This is optional.
 * @param boldText The bold text to be displayed. This text is clickable.
 * @param onBoldTextClick The function to be triggered when the bold text is clicked.
 * @param modifier The modifier to be applied to the text component.
 */
@Composable
fun ClickableTextComponent(
    normalText: String? = null,
    boldText: String,
    onBoldTextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        normalText?.let {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = colorResource(id = R.color.light_green))) {
                        append(it)
                    }
                }, style = MaterialTheme.typography.bodyMedium
            )
        }
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold, color = colorResource(id = R.color.green)
                    )
                ) {
                    append(boldText)
                }
            },
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.clickable(onClick = onBoldTextClick)
        )
    }
}