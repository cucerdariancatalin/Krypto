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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vitassalvantes.krypto.KryptoViewModel
import com.vitassalvantes.krypto.R
import com.vitassalvantes.krypto.ciphers.CiphersInfo.listOfAllCiphers
import com.vitassalvantes.krypto.ciphers.KryptoCipher
import com.vitassalvantes.krypto.data.Correspondence

/**
 * Screen to en- or decrypt message
 */
@Composable
fun CorrespondenceDetailsScreen(viewModel: KryptoViewModel, correspondenceId: Int) {

    val currentCorrespondence =
        viewModel.getCorrespondenceById(id = correspondenceId)
    val currentCipher = listOfAllCiphers[currentCorrespondence.cipherIndex]

    CorrespondenceDetailsScreenContent(
        currentCorrespondence = currentCorrespondence,
        currentCipher = currentCipher
    )

}

/**
 * UI content of the [CorrespondenceDetailsScreen].
 *
 * @param currentCorrespondence selected on the [CorrespondencesScreen] correspondence.
 * @param currentCipher current cipher to en- and decrypt messages.
 */
@Composable
fun CorrespondenceDetailsScreenContent(
    currentCorrespondence: Correspondence,
    currentCipher: KryptoCipher
) {
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
            label = { Text(text = stringResource(R.string.message)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(64.dp))

        // Context for Toast
        val context = LocalContext.current

        // Clipboard manager to copy the message to the clipboard
        val clipboardManager = LocalClipboardManager.current

        // The Toast "Copied to clipboard"
        val toast = Toast.makeText(
            context,
            context.getString(R.string.copied_to_clipboard),
            Toast.LENGTH_SHORT
        )

        // Buttons to decrypt and encrypt the input message
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                inputMessage = currentCipher.decrypt(inputMessage, currentCorrespondence.key)
                clipboardManager.setText(AnnotatedString(text = inputMessage))
                toast.show()
            }) {
                Text(text = stringResource(R.string.decryption))
            }

            Spacer(modifier = Modifier.width(32.dp))

            Button(onClick = {
                inputMessage = currentCipher.encrypt(inputMessage, currentCorrespondence.key)
                clipboardManager.setText(AnnotatedString(text = inputMessage))
                toast.show()
            }) {
                Text(text = stringResource(R.string.encryption))
            }
        }
    }
}

@Preview(
    name = "CorrespondenceDetailsScreen",
    showSystemUi = true
)
@Composable
fun PreviewCorrespondenceDetailsScreen() {
    val testCorrespondence = Correspondence(
        correspondenceName = "TestCorrespondence",
        cipherIndex = R.string.caesar_cipher_name,
        key = "TestCorrespondence"
    )

    val testCipher = listOfAllCiphers[0]

    CorrespondenceDetailsScreenContent(currentCorrespondence = testCorrespondence, testCipher)
}