package com.vitassalvantes.krypto.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vitassalvantes.krypto.ciphers.CiphersInfo.listOfAllCiphers
import com.vitassalvantes.krypto.ciphers.KryptoCipher
import com.vitassalvantes.krypto.navigation.KryptoScreen
import com.vitassalvantes.krypto.ui.components.KryptoCard

/**
 * Screen with a list of ciphers used in the app.
 */
@Composable
fun CiphersScreen(navController: NavHostController) {

    CiphersScreenContent { cipher ->
        navController.navigate(
            KryptoScreen.CipherDetailsScreen.route + "/${listOfAllCiphers.indexOf(cipher)}"
        ) {
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
}

/**
 * UI content of the [CiphersScreen].
 *
 * @param onCardClick navigation to the [CipherDetailsScreen].
 */
@Composable
fun CiphersScreenContent(onCardClick: (KryptoCipher) -> Unit) {
    LazyColumn(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(listOfAllCiphers) { cipher ->
            KryptoCard(
                cardName = stringResource(id = cipher.name),
                cardIcon = cipher.icon,
                onClickListener = {
                    onCardClick(cipher)
                }
            )
        }
    }
}

@Preview(
    name = "CiphersScreen",
    showSystemUi = true
)
@Composable
fun PreviewCiphersScreen() {
    CiphersScreenContent {}
}