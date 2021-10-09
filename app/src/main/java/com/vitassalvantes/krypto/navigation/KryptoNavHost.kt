package com.vitassalvantes.krypto.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vitassalvantes.krypto.KryptoViewModel
import com.vitassalvantes.krypto.ui.screens.CipherDetailsScreen
import com.vitassalvantes.krypto.ui.screens.CiphersScreen
import com.vitassalvantes.krypto.ui.screens.CreatingNewRoomScreen
import com.vitassalvantes.krypto.ui.screens.RoomsScreen

/**
 *  Navigation destination management
 */
@Composable
fun KryptoNavHost(
    navController: NavHostController,
    viewModel: KryptoViewModel,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = KryptoScreen.RoomsScreen.route,
        Modifier.padding(innerPadding)
    ) {
        composable(route = KryptoScreen.RoomsScreen.route) {
            RoomsScreen(viewModel = viewModel)
        }

        composable(route = KryptoScreen.CiphersScreen.route) {
            CiphersScreen(navController = navController)
        }

        composable(route = KryptoScreen.CreatingNewRoom.route) {
            CreatingNewRoomScreen(viewModel = viewModel)
        }

        composable(
            route = KryptoScreen.CipherDetailsScreen.route + "/{cipherIndex}",
            arguments = listOf(
                navArgument("cipherIndex") { type = NavType.IntType; defaultValue = 0 })
        ) { backStackEntry ->
            CipherDetailsScreen(backStackEntry.arguments?.getInt("cipherIndex") ?: 0)
        }
    }
}