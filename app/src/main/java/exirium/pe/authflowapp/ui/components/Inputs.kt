package exirium.pe.authflowapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

/**
 * A Composable function that creates a text input field with a label.
 *
 * @param value The current value of the text field.
 * @param onValueChange A function that gets called when the text field value changes.
 * @param label The label for the text field.
 * @param modifier The modifier to be applied to the text field.
 * @param keyboardType The type of keyboard to be used for the text field.
 */
@Composable
fun TextInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        value = value,
        textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
        onValueChange = onValueChange,
        label = { Text(label) },
        maxLines = 1,
        keyboardOptions = keyboardType,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
        )
    )
}

/**
 * A Composable function that creates a password input field with a label.
 * The password field includes a button to toggle visibility of the password.
 *
 * @param value The current value of the password field.
 * @param onValueChange A function that gets called when the password field value changes.
 * @param label The label for the password field.
 * @param modifier The modifier to be applied to the password field.
 */
@Composable
fun PasswordInput(
    value: String, onValueChange: (String) -> Unit, label: String, modifier: Modifier = Modifier
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
        onValueChange = onValueChange,
        label = { Text(label) },
        maxLines = 1,
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            TextButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Text(text = if (passwordVisibility) "Hide" else "View")
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedSupportingTextColor = Color.Black,
            unfocusedSupportingTextColor = Color.Black
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}
