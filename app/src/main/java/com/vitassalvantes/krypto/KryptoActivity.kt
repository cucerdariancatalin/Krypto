package com.vitassalvantes.krypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vitassalvantes.krypto.navigation.KryptoBottomAppBar
import com.vitassalvantes.krypto.navigation.KryptoFloatingActionButton
import com.vitassalvantes.krypto.navigation.KryptoScreen
import com.vitassalvantes.krypto.ui.screens.CiphersScreen
import com.vitassalvantes.krypto.ui.screens.RoomsScreen
import com.vitassalvantes.krypto.ui.theme.KryptoTheme

/**
 * The entry point to the program.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KryptoApp()
        }
    }
}

/**
 * Managing the main content in the app, that consists of a Scaffold with [KryptoBottomAppBar]
 * and NavHost that controls the display of screens.
 */
@Composable
fun KryptoApp() {
    KryptoTheme {
        /**
         * The central API for the Navigation component.
         * It is stateful and keeps track of the back stack of composables that make up the screens
         * in this app and the state of each screen.
         */
        val navController = rememberNavController()

        Scaffold(
            bottomBar = { KryptoBottomAppBar(navController) },
            floatingActionButton = { KryptoFloatingActionButton() },
            floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = true
        ) { innerPadding ->

            // Navigation destination management
            NavHost(
                navController = navController,
                startDestination = KryptoScreen.RoomsScreen.route,
                Modifier.padding(innerPadding)
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