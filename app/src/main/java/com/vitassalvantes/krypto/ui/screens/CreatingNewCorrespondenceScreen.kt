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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.vitassalvantes.krypto.R
import com.vitassalvantes.krypto.ciphers.CiphersInfo.listOfAllCiphers
import com.vitassalvantes.krypto.model.KryptoViewModel
import com.vitassalvantes.krypto.navigation.KryptoScreen

/**
 * Screen to create a custom correspondence
 */
@Composable
fun CreatingNewCorrespondenceScreen(viewModel: KryptoViewModel, navController: NavHostController) {
    // List with names of all ciphers
    val namesOfAllCiphers = listOfAllCiphers.map { it.name }

    // Context for Toast
    val context = LocalContext.current

    CreatingNewCorrespondenceScreenContent(
        namesOfAllCiphers = namesOfAllCiphers,
        onCreateButtonClick = { inputCorrespondenceName, inputKey, selectedCipherName ->
            if (viewModel.createNewCorrespondence(
                    correspondenceName = inputCorrespondenceName,
                    cipherName = selectedCipherName,
                    key = inputKey,
                    context = context
                )
            ) {
                // Navigate to CorrespondencesScreen
                navController.navigate(KryptoScreen.CorrespondencesScreen.route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = false
                }
            }
        }
    )
}

/**
 * UI content of the [CreatingNewCorrespondenceScreen].
 *
 * @param namesOfAllCiphers list of names of all ciphers.
 * @param onCreateButtonClick create new custom correspondence.
 */
@Composable
fun CreatingNewCorrespondenceScreenContent(
    namesOfAllCiphers: List<Int>,
    onCreateButtonClick: (inputCorrespondenceName: String, inputKey: String, selectedCipherName: Int) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = stringResource(R.string.cipher), style = MaterialTheme.typography.h5)

        //Radio buttons state
        val (selectedCipherName, onCipherNameSelected) = rememberSaveable {
            mutableStateOf(
                namesOfAllCiphers[0]
            )
        }

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
                            selected = (nameOfCipher == selectedCipherName),
                            onClick = { onCipherNameSelected(nameOfCipher) },
                            role = Role.RadioButton
                        )
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (nameOfCipher == selectedCipherName),
                        onClick = null
                    ) // null recommended for accessibility with screenreaders
                    Text(
                        text = stringResource(nameOfCipher),
                        Modifier.padding(start = 4.dp)
                    )
                }
            }
        }

        // Name of the new correspondence from user
        var inputCorrespondenceName by rememberSaveable { mutableStateOf("") }

        // Input field to set a name of the new correspondence
        OutlinedTextField(
            value = inputCorrespondenceName,
            onValueChange = { inputCorrespondenceName = it },
            label = { Text("Name of Correspondence") },
            leadingIcon = { Icon(Icons.Default.Edit, contentDescription = "Icon Label") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

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

        // Button to create the new correspondence and to navigate to this
        Button(
            onClick = {
                onCreateButtonClick(
                    inputCorrespondenceName,
                    inputKey,
                    selectedCipherName
                )
            },
            Modifier
                .padding(top = 64.dp)
                .align(CenterHorizontally)
        ) {
            Text(text = "CREATE ${inputCorrespondenceName.uppercase()}")
        }
    }
}

@Preview(
    name = "CreatingNewCorrespondence",
    showSystemUi = true
)
@Composable
fun PreviewCreatingNewCorrespondenceScreen() {
    val namesOfAllCiphers = listOfAllCiphers.map { it.name }
    CreatingNewCorrespondenceScreenContent(
        namesOfAllCiphers = namesOfAllCiphers,
        onCreateButtonClick = { _, _, _ -> }
    )
}