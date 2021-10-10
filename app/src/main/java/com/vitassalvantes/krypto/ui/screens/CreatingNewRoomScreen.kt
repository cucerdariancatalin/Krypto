package com.vitassalvantes.krypto.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.vitassalvantes.krypto.KryptoViewModel
import com.vitassalvantes.krypto.ciphers.CiphersInfo.listOfAllCiphers

/**
 * Screen to create a custom room
 */
@Composable
fun CreatingNewRoomScreen(viewModel: KryptoViewModel) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "Cipher", style = MaterialTheme.typography.h5)

        /**
         * List with names of all ciphers from [listOfAllCiphers]
         */
        val namesOfAllCiphers = listOfAllCiphers.map { it.name }

        //Radio buttons state
        val (selectedName, onNameSelected) = rememberSaveable { mutableStateOf(namesOfAllCiphers[0]) }

        // Column with radio buttons to choose a cipher
        Column(
            Modifier
                .selectableGroup(),
            Arrangement.SpaceEvenly
        ) {
            namesOfAllCiphers.forEach { nameOfCipher ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (nameOfCipher == selectedName),
                            onClick = { onNameSelected(nameOfCipher) },
                            role = Role.RadioButton
                        )
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (nameOfCipher == selectedName),
                        onClick = null
                    ) // null recommended for accessibility with screenreaders
                    Text(
                        text = nameOfCipher,
                        Modifier.padding(start = 4.dp)
                    )
                }
            }
        }

        // Name of the new room from user
        var inputNameOfRoom by rememberSaveable { mutableStateOf("") }

        // Input field to set a name of the new room
        OutlinedTextField(
            value = inputNameOfRoom,
            onValueChange = { inputNameOfRoom = it },
            label = { Text("Name of Room") },
            leadingIcon = { Icon(Icons.Default.Edit, contentDescription = "Icon Label") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // TODO: 10.10.2021 if nameOfCipher != Atbash Cipher
        // Key from user for the chose cipher
        var inputKey by rememberSaveable { mutableStateOf("") }

        // Visibility of the user input for the security
        var inputKeyVisibility by rememberSaveable { mutableStateOf(false) }

        // Input field to set a key for the chose cipher
        OutlinedTextField(
            value = inputKey,
            onValueChange = { inputKey = it },
            label = { Text("Key") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Icon Key") },
            trailingIcon = {
                val image = if (inputKeyVisibility) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
                IconButton(onClick = {
                    inputKeyVisibility = !inputKeyVisibility
                }) {
                    Icon(imageVector = image, "Visibility")
                }
            },
            visualTransformation = if (inputKeyVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        // Button to create the new room and to navigate to this
        Button(
            onClick = { /*TODO add new room and navigate to this room*/ },
            Modifier
                .padding(top = 64.dp)
                .align(CenterHorizontally)
        ) {
            Text(text = "CREATE ${inputNameOfRoom.uppercase()}")
        }
    }
}