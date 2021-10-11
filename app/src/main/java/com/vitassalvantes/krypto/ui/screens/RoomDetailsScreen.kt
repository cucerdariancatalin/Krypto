package com.vitassalvantes.krypto.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable

@Composable
fun RoomDetailsScreen(roomIndex: Int) {
    Column {

        TextField(value = "", onValueChange = {})

        Row {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Decryption")
            }

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Encryption")
            }
        }
    }
}