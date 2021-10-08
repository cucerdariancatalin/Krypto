package com.vitassalvantes.krypto.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Card that consists of an icon and a name. Used as an item in the LazyColumn
 * for [com.vitassalvantes.krypto.ui.screens.CiphersScreen] and
 * [com.vitassalvantes.krypto.ui.screens.RoomsScreen].
 */
@Composable
fun KryptoCard(cardName: String, cardIcon: ImageVector) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(cardIcon, cardName)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = cardName)
        }
    }
}

@Preview(
    name = "KryptoCard",
    showBackground = true
)
@Composable
fun PreviewKryptoCard() {
    KryptoCard(cardName = "Example", cardIcon = Icons.Filled.Face)
}