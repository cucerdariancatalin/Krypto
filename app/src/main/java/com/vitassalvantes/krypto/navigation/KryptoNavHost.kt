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
        startDestination = KryptoScreen.CorrespondencesScreen.route,
        Modifier.padding(innerPadding)
    ) {
        composable(route = KryptoScreen.CorrespondencesScreen.route) {
            CorrespondencesScreen(viewModel = viewModel, navController = navController)
        }

        composable(route = KryptoScreen.CiphersScreen.route) {
            CiphersScreen(navController = navController)
        }

        composable(route = KryptoScreen.CreatingNewCorrespondence.route) {
            CreatingNewCorrespondenceScreen(viewModel = viewModel, navController = navController)
        }

        composable(
            route = KryptoScreen.CipherDetailsScreen.route + "/{cipherIndex}",
            arguments = listOf(
                navArgument(name = "cipherIndex") { type = NavType.IntType; defaultValue = 0 })
        ) { backStackEntry ->
            CipherDetailsScreen(cipherIndex = backStackEntry.arguments?.getInt("cipherIndex") ?: 0)
        }

        composable(
            route = KryptoScreen.CorrespondenceDetailsScreen.route + "/{correspondenceId}", arguments = listOf(
                navArgument(name = "correspondenceId") { type = NavType.IntType; defaultValue = 0 })
        ) { backStackEntry ->
            CorrespondenceDetailsScreen(
                viewModel = viewModel,
                correspondenceId = backStackEntry.arguments?.getInt("correspondenceId") ?: 0
            )
        }
    }
}