package com.vitassalvantes.krypto.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vitassalvantes.krypto.ciphers.CaesarCipher
import com.vitassalvantes.krypto.ciphers.KryptoCipher
import com.vitassalvantes.krypto.ui.components.KryptoCard

/**
 * Screen with a list of ciphers used in the app.
 */
@Composable
fun CiphersScreen() {
    /**
     * List of ciphers used in the app
     */
    val ciphers = listOf<KryptoCipher>(CaesarCipher(), CaesarCipher(), CaesarCipher())

    LazyColumn(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(ciphers) { cipher ->
            KryptoCard(cardName = cipher.name, cardIcon = cipher.icon)
        }
    }
}