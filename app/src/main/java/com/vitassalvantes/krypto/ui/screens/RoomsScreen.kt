package com.vitassalvantes.krypto.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vitassalvantes.krypto.KryptoRoom
import com.vitassalvantes.krypto.KryptoViewModel
import com.vitassalvantes.krypto.ciphers.CaesarCipher

@Composable
fun RoomsScreen(viewModel: KryptoViewModel) {
    LazyColumn(Modifier.padding(16.dp)) {
        items(viewModel.listOfRooms) { room ->
            RoomCard(room = room)
        }
    }
}

@Composable
fun RoomCard(room: KryptoRoom) {
    Card(
        Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(room.cipher.icon, room.name)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = room.name)
        }
    }
}

@Preview(
    name = "RoomCard"
)
@Composable
fun PreviewRoomCard() {
    RoomCard(room = KryptoRoom("Test Room", CaesarCipher()))
}