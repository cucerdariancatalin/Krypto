package com.vitassalvantes.krypto.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.vitassalvantes.krypto.ciphers.CiphersInfo
import com.vitassalvantes.krypto.model.KryptoViewModel
import com.vitassalvantes.krypto.navigation.KryptoScreen
import com.vitassalvantes.krypto.ui.components.KryptoCard

/**
 * Screen with a list of created rooms.
 */
@Composable
fun RoomsScreen(viewModel: KryptoViewModel, navController: NavHostController) {

    val listOfAllCorrespondences = viewModel.listOfAllCorrespondences.observeAsState(listOf()).value

    // Boolean variable to display the dialog
    var openDialog by rememberSaveable { mutableStateOf(false) }

    // Index of long pressed room for dialog
    var pressedRoomIndex by rememberSaveable { mutableStateOf(0) }

    // Context for Toast
    val context = LocalContext.current

    // Dialog for editing or deleting the room
    if (openDialog) {
        val pressedCorrespondence =
            viewModel.findCorrespondenceById(pressedRoomIndex)
        // All info about selected room
        val cipherInfo = "Name: ${pressedCorrespondence.correspondenceName}\n" +
                "Cipher: ${stringResource(id = pressedCorrespondence.cipherName)}\n" +
                "Key: ${pressedCorrespondence.key}"

        AlertDialog(
            onDismissRequest = { openDialog = !openDialog },
            title = { Text(text = "Delete this room?") },
            text = {
                Text(
                    text = cipherInfo
                )
            },
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = {
                        // Remove this room from listOfRooms
                        Toast.makeText(
                            context,
                            "Removed ${pressedCorrespondence.correspondenceName}",
                            Toast.LENGTH_SHORT
                        ).show()
                        viewModel.deleteCurrentCorrespondence(pressedCorrespondence)
                        openDialog = !openDialog
                    }) {
                        Text(text = "Remove".uppercase())
                    }

                    TextButton(onClick = {
                        // Cancel deleting
                        openDialog = !openDialog
                    }) {
                        Text(text = "Cancel".uppercase())
                    }
                }
            })
    }

    LazyColumn(Modifier.padding(16.dp)) {
        items(listOfAllCorrespondences) { correspondence ->
            KryptoCard(
                cardName = correspondence.correspondenceName,
                cardIcon = CiphersInfo.getCipher(correspondence.cipherName).icon,
                onClickListener = {
                    navController.navigate(
                        KryptoScreen.RoomDetailsScreen.route + "/${correspondence.id}"
                    ) {
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
                },
                onLongClickListener = {
                    // Call the dialog
                    openDialog = !openDialog
                    pressedRoomIndex = correspondence.id
                }
            )
        }
    }
}