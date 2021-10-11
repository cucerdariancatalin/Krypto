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
import com.vitassalvantes.krypto.ui.screens.*

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
            CreatingNewRoomScreen(viewModel = viewModel, navController = navController)
        }

        composable(
            route = KryptoScreen.CipherDetailsScreen.route + "/{cipherIndex}",
            arguments = listOf(
                navArgument(name = "cipherIndex") { type = NavType.IntType; defaultValue = 0 })
        ) { backStackEntry ->
            CipherDetailsScreen(cipherIndex = backStackEntry.arguments?.getInt("cipherIndex") ?: 0)
        }

        composable(
            route = KryptoScreen.RoomDetailsScreen.route + "/{roomIndex}", arguments = listOf(
                navArgument(name = "roomIndex") { type = NavType.IntType; defaultValue = 0 })
        ) { backStackEntry ->
            RoomDetailsScreen(
                viewModel = viewModel,
                roomIndex = backStackEntry.arguments?.getInt("roomIndex") ?: 0
            )
        }
    }
}