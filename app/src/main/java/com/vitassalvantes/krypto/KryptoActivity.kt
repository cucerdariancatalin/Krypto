package com.vitassalvantes.krypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vitassalvantes.krypto.navigation.KryptoBottomAppBar
import com.vitassalvantes.krypto.navigation.KryptoFloatingActionButton
import com.vitassalvantes.krypto.navigation.KryptoScreen
import com.vitassalvantes.krypto.ui.screens.CiphersScreen
import com.vitassalvantes.krypto.ui.screens.RoomsScreen
import com.vitassalvantes.krypto.ui.theme.KryptoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KryptoApp()
        }
    }
}

@Composable
fun KryptoApp() {
    KryptoTheme {
        val kryptoNavController = rememberNavController()

        Scaffold(
            bottomBar = { KryptoBottomAppBar(kryptoNavController) },
            floatingActionButton = { KryptoFloatingActionButton() },
            floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = true
        ) { innerPadding ->
            NavHost(
                navController = kryptoNavController,
                startDestination = KryptoScreen.RoomsScreen.route
            ) {
                composable(route = KryptoScreen.RoomsScreen.route) {
                    RoomsScreen()
                }

                composable(route = KryptoScreen.CiphersScreen.route) {
                    CiphersScreen()
                }
            }
        }
    }
}