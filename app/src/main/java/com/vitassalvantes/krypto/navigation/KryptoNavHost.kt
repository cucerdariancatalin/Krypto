package com.vitassalvantes.krypto.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vitassalvantes.krypto.ui.screens.CiphersScreen
import com.vitassalvantes.krypto.ui.screens.RoomsScreen

/**
 *  Navigation destination management
 */
@Composable
fun KryptoNavHost(navController: NavHostController, innerPadding: PaddingValues) {
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