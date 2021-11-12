package com.vitassalvantes.krypto.navigation

/**
 * Class for navigation that contains all screens.
 *
 * Don't forget add new screens to [KryptoBottomAppBar] and [KryptoNavHost]!
 */
sealed class KryptoScreen(val route: String) {

    /**
     * Object for navigation to [com.vitassalvantes.krypto.ui.screens.CorrespondencesScreen].
     */
    object CorrespondencesScreen : KryptoScreen("correspondences_screen")

    /**
     * Object for navigation to [com.vitassalvantes.krypto.ui.screens.CiphersScreen].
     */
    object CiphersScreen : KryptoScreen("ciphers_screen")

    /**
     * Object for navigation to [com.vitassalvantes.krypto.ui.screens.CreatingNewCorrespondenceScreen].
     */
    object CreatingNewCorrespondence : KryptoScreen("creating_new_correspondence_screen")

    /**
     * Object for navigation to [com.vitassalvantes.krypto.ui.screens.CipherDetailsScreen].
     */
    object CipherDetailsScreen : KryptoScreen("cipher_details_screen")

    /**
     * Object for navigation to [com.vitassalvantes.krypto.ui.screens.CorrespondenceDetailsScreen].
     */
    object CorrespondenceDetailsScreen : KryptoScreen("correspondence_details_screen")
}