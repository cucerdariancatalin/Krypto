package com.vitassalvantes.krypto.navigation

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vitassalvantes.krypto.R
import com.vitassalvantes.krypto.ui.theme.Shapes

/**
 * The bottom app bar that provides navigation.
 */
@Composable
fun KryptoBottomAppBar(navController: NavHostController) {

    BottomAppBar(cutoutShape = Shapes.small) {
        BottomNavigation {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            /**
             * Navigate to [com.vitassalvantes.krypto.ui.screens.CorrespondencesScreen]
             */
            BottomNavigationItem(
                icon = {
                    Icon(
                        Icons.Filled.List,
                        contentDescription = stringResource(id = R.string.correspondences)
                    )
                },
                label = { Text(text = stringResource(id = R.string.correspondences)) },
                selected = currentDestination?.hierarchy?.any { it.route == KryptoScreen.CorrespondencesScreen.route } == true,
                onClick = {
                    navController.navigate(KryptoScreen.CorrespondencesScreen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = false
                    }
                }
            )

            /**
             * Navigate to [com.vitassalvantes.krypto.ui.screens.CiphersScreen]
             */
            BottomNavigationItem(
                icon = {
                    Icon(
                        Icons.Filled.Code,
                        contentDescription = stringResource(id = R.string.ciphers)
                    )
                },
                label = { Text(text = stringResource(id = R.string.ciphers)) },
                selected = currentDestination?.hierarchy?.any { it.route == KryptoScreen.CiphersScreen.route } == true,
                onClick = {
                    navController.navigate(KryptoScreen.CiphersScreen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = false
                    }
                }
            )
        }
    }
}

/**
 * The floating action button that used with the [KryptoBottomAppBar]
 */
@Composable
fun KryptoFloatingActionButton(navController: NavHostController) {
    FloatingActionButton(
        onClick = {
            navController.navigate(KryptoScreen.CreatingNewCorrespondence.route) {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = false
            }
        },
        shape = Shapes.small
    ) {
        Icon(Icons.Filled.Add, stringResource(id = R.string.creating_a_new_room))
    }
}

@Preview(
    name = "KryptoBottomAppBar",
    showBackground = true
)
@Composable
fun PreviewKryptoBottomAppBar() {
    KryptoBottomAppBar(rememberNavController())
}

@Preview(
    name = "KryptoFloatingActionButton",
    showBackground = true
)
@Composable
fun PreviewKryptoFloatingActionButton() {
    KryptoFloatingActionButton(navController = rememberNavController())
}
