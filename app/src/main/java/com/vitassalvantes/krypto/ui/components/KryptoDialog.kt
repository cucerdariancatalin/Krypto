package com.vitassalvantes.krypto.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Alert dialog to interact with the user.
 *
 * @param title the title of the dialog.
 * @param text the text which contains info about the interaction.
 * @param acceptButtonText the text of the accept button.
 * @param declineButtonText the text of the decline button.
 * @param onAcceptButtonClickListener the action by clicking the accept button.
 * @param onDeclineButtonClickListener the action by clicking the decline button.
 * @param onDismissRequest the action by clicking outside or pressing the back button.
 */
@Composable
fun KryptoDialog(
    title: String,
    text: String = "",
    acceptButtonText: String,
    declineButtonText: String,
    onAcceptButtonClickListener: () -> Unit,
    onDeclineButtonClickListener: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        title = { Text(text = title) },
        text = { Text(text = text) },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                // The accept button
                TextButton(onClick = { onAcceptButtonClickListener() }) {
                    Text(text = acceptButtonText)
                }

                // The decline button
                TextButton(onClick = { onDeclineButtonClickListener() }) {
                    Text(text = declineButtonText)
                }
            }
        }
    )
}