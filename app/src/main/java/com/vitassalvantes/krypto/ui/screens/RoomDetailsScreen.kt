package com.vitassalvantes.krypto.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.vitassalvantes.krypto.ciphers.CiphersInfo
import com.vitassalvantes.krypto.model.KryptoViewModel

/**
 * Screen to en- or decrypt message
 */
@Composable
fun RoomDetailsScreen(viewModel: KryptoViewModel, roomIndex: Int) {

    // Current room that is displayed
    val currentCorrespondence =
        viewModel.getCorrespondenceById(id = roomIndex)
    val currentCipher = CiphersInfo.getCipher(currentCorrespondence.cipherName)

    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = currentCorrespondence.correspondenceName,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Input message from user
        var inputMessage by rememberSaveable { mutableStateOf("") }

        // Text field contains input message from user
        OutlinedTextField(
            value = inputMessage,
            onValueChange = { inputMessage = it },
            label = { Text(text = "Message") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(64.dp))

        // Context for Toast
        val context = LocalContext.current

        // Clipboard manager to copy the message to the clipboard
        val clipboardManager = LocalClipboardManager.current

        // Buttons to decrypt and encrypt the input message
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                inputMessage = currentCipher.decrypt(inputMessage, currentCorrespondence.key)
                clipboardManager.setText(AnnotatedString(text = inputMessage))
                Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "Decryption")
            }

            Spacer(modifier = Modifier.width(32.dp))

            Button(onClick = {
                inputMessage = currentCipher.encrypt(inputMessage, currentCorrespondence.key)
                clipboardManager.setText(AnnotatedString(text = inputMessage))
                Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "Encryption")
            }
        }
    }
}