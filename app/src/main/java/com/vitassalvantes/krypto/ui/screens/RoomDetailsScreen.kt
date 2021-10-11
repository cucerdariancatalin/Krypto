package com.vitassalvantes.krypto.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vitassalvantes.krypto.KryptoViewModel
import com.vitassalvantes.krypto.R

/**
 * Screen to en- or decrypt message
 */
@Composable
fun RoomDetailsScreen(viewModel: KryptoViewModel, roomIndex: Int) {

    // Current room that is displayed
    val currentRoom = viewModel.listOfRooms[roomIndex]

    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = currentRoom.name,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Input message from user
        var inputMessage by rememberSaveable { mutableStateOf("") }

        // Text field contains input message from user and
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = inputMessage,
                onValueChange = { inputMessage = it },
                label = { Text(text = "Message") },
                modifier = Modifier.fillMaxSize()
            )

            Image(
                imageVector = Icons.Filled.Share,
                contentDescription = stringResource(R.string.send_message),
                Modifier
                    .clickable { }
                    .align(Alignment.BottomEnd)
                    .padding(4.dp)
            )
        }

        Spacer(modifier = Modifier.height(64.dp))

        // Buttons to decrypt and encrypt the input message
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = { inputMessage = currentRoom.cipher.decrypt(inputMessage) }) {
                Text(text = "Decryption")
            }

            Spacer(modifier = Modifier.width(32.dp))

            Button(onClick = { inputMessage = currentRoom.cipher.encrypt(inputMessage) }) {
                Text(text = "Encryption")
            }
        }
    }
}