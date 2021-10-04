package com.vitassalvantes.krypto.navigation

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
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
             * List of [KryptoScreen] screens to display in bottom app bar.
             */
            val items = listOf(
                KryptoScreen.RoomsScreen,
                KryptoScreen.CiphersScreen
            )

            items.forEach { kryptoScreen ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            kryptoScreen.iconId,
                            contentDescription = stringResource(id = kryptoScreen.labelId)
                        )
                    },
                    label = { Text(text = stringResource(id = kryptoScreen.labelId)) },
                    selected = currentDestination?.hierarchy?.any { it.route == kryptoScreen.route } == true,
                    onClick = {
                        navController.navigate(kryptoScreen.route) {
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
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

/**
 * The floating action button that used with the [KryptoBottomAppBar]
 */
@Composable
fun KryptoFloatingActionButton() {
    FloatingActionButton(onClick = { /*TODO Create a new room*/ }, shape = Shapes.small) {
        Icon(Icons.Filled.Add, "Create a new room")
    }
}

@Preview(
    name = "KryptoBottomAppBar",
    showBackground = true
)
@Composable
fun PreviewKryptoBottomAppBar() {
    KryptoBottomAppBar(navController = rememberNavController())
}

@Preview(
    name = "KryptoFloatingActionButton",
    showBackground = true
)
@Composable
fun PreviewKryptoFloatingActionButton() {
    KryptoFloatingActionButton()
}
