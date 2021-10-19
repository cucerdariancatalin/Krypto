package com.vitassalvantes.krypto.ciphers

import androidx.annotation.StringRes

/**
 * Singleton to access the list of all implemented ciphers
 */
object CiphersInfo {
    /**
     * List of ciphers used in the app
     */
    val listOfAllCiphers = listOf<KryptoCipher>(
        CaesarCipher()
    )

    /**
     * Getting a cipher by its [com.vitassalvantes.krypto.ciphers.KryptoCipher.name]
     */
    fun getCipher(@StringRes cipherName: Int): KryptoCipher {
        return listOfAllCiphers.find { it.name == cipherName } ?: listOfAllCiphers[0]
        // TODO: 17.10.2021 throw CipherNotFoundException
    }
}
