package com.vitassalvantes.krypto.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.vitassalvantes.krypto.R

/**
 * Class for navigation that contains all screens.
 *
 * Don't forget add new screens to [KryptoBottomAppBar] and [KryptoNavHost]!
 */
sealed class KryptoScreen(val route: String, val iconId: ImageVector, val labelId: Int) {

    /**
     * Object for navigation to [com.vitassalvantes.krypto.ui.screens.RoomsScreen].
     */
    object RoomsScreen : KryptoScreen("rooms_screen", Icons.Filled.List, R.string.rooms)

    /**
     * Object for navigation to [com.vitassalvantes.krypto.ui.screens.CiphersScreen].
     */
    object CiphersScreen : KryptoScreen("ciphers_screen", Icons.Filled.Code, R.string.ciphers)

    /**
     * Object for navigation to [com.vitassalvantes.krypto.ui.screens.CreatingNewRoomScreen].
     */
    object CreatingNewRoom : KryptoScreen(
        "creating_new_room_screen", Icons.Filled.Add, R.string.creating_a_new_room
    )

    /**
     * Object for navigation to [com.vitassalvantes.krypto.ui.screens.CipherDetailsScreen].
     */
    object CipherDetailsScreen :
        KryptoScreen("cipher_details_screen", Icons.Filled.Add, R.string.cipher_details_screen)

    /**
     * Object for navigation to [com.vitassalvantes.krypto.ui.screens.RoomDetailsScreen].
     */
    object RoomDetailsScreen :
        KryptoScreen("room_details_screen", Icons.Filled.Add, R.string.room_details_screen)

    /**
     * Object for navigation to [com.vitassalvantes.krypto.ui.screens.RoomInfoScreen].
     */
    object RoomInfoScreen :
        KryptoScreen("room_info_screen", Icons.Filled.Add, R.string.room_details_screen)
}