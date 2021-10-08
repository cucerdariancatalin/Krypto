package com.vitassalvantes.krypto.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vitassalvantes.krypto.KryptoViewModel
import com.vitassalvantes.krypto.ui.components.KryptoCard

/**
 * Screen with a list of created rooms.
 */
@Composable
fun RoomsScreen(viewModel: KryptoViewModel) {
    LazyColumn(Modifier.padding(16.dp)) {
        items(viewModel.listOfRooms) { room ->
            KryptoCard(cardName = room.cipher.name, cardIcon = room.cipher.icon)
        }
    }
}