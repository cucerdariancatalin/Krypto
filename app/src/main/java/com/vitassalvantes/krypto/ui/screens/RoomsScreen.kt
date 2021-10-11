package com.vitassalvantes.krypto.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.vitassalvantes.krypto.KryptoViewModel
import com.vitassalvantes.krypto.navigation.KryptoScreen
import com.vitassalvantes.krypto.ui.components.KryptoCard

/**
 * Screen with a list of created rooms.
 */
@Composable
fun RoomsScreen(viewModel: KryptoViewModel, navController: NavHostController) {
    LazyColumn(Modifier.padding(16.dp)) {
        items(viewModel.listOfRooms) { room ->
            KryptoCard(
                cardName = room.name,
                cardIcon = room.cipher.icon,
            ) {
                navController.navigate(
                    KryptoScreen.RoomDetailsScreen.route + "/${viewModel.listOfRooms.indexOf(room)}"
                ) {// Pop up to the start destination of the graph to
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
    }
}