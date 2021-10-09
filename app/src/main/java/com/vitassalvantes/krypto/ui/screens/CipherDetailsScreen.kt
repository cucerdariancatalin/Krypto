package com.vitassalvantes.krypto.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CipherDetailsScreen() {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        Text(text = "Title")
        Text(
            text = "Description. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed" +
                    " diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat," +
                    " sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum." +
                    " Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet." +
                    " Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod" +
                    " tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua." +
                    " At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren," +
                    " no sea takimata sanctus est Lorem ipsum dolor sit amet."
        )
    }
}