package com.vitassalvantes.krypto.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.ui.graphics.vector.ImageVector
import com.vitassalvantes.krypto.R

/**
 * Class for navigation that contains all screens.
 */
sealed class KryptoScreen(val route: String, val iconId: ImageVector, val labelId: Int) {

    /**
     * Object for navigation to [com.vitassalvantes.krypto.ui.screens.RoomsScreen].
     */
    object RoomsScreen : KryptoScreen("rooms_screen", Icons.Filled.Edit, R.string.rooms)

    /**
     * Object for navigation to [com.vitassalvantes.krypto.ui.screens.CiphersScreen].
     */
    object CiphersScreen : KryptoScreen("ciphers _screen", Icons.Filled.Lock, R.string.ciphers)
}
