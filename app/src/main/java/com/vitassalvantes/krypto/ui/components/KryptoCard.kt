package com.vitassalvantes.krypto.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vitassalvantes.krypto.R

/**
 * Card that consists of an icon and a name. Used as an item in the LazyColumn
 * for [com.vitassalvantes.krypto.ui.screens.CiphersScreen] and
 * [com.vitassalvantes.krypto.ui.screens.CorrespondencesScreen].
 */
@Composable
fun KryptoCard(
    cardName: String,
    @DrawableRes cardIcon: Int,
    onClickListener: () -> Unit,
    onLongClickListener: () -> Unit = {}
) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { onClickListener() },
                    onLongPress = { onLongClickListener() }
                )
            },
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                painter = painterResource(id = cardIcon),
                contentDescription = cardName,
                Modifier.size(100.dp)
            )
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
    KryptoCard(cardName = "Example", cardIcon = R.drawable.caesar_cipher_icon, onClickListener = {})
}