package com.vitassalvantes.krypto.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.vitassalvantes.krypto.model.KryptoViewModel
import com.vitassalvantes.krypto.navigation.KryptoScreen

/**
 * Screen to see all info about the room and to change it
 */
@Composable
fun RoomInfoScreen(viewModel: KryptoViewModel, navController: NavHostController, roomIndex: Int) {
    // Current room that is displayed
    val currentRoom = viewModel.listOfRooms[roomIndex]

    Column {
        Text(text = currentRoom.name)
        Text(text = currentRoom.cipher.name)
        Text(text = currentRoom.key)
        Button(onClick = {
            navController.navigate(KryptoScreen.RoomDetailsScreen.route + "/${roomIndex}") {
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
        }) {
            Text(text = "Save".uppercase())
        }
    }
}