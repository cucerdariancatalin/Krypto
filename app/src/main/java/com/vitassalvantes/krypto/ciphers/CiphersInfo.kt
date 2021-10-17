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
        listOfAllCiphers.forEach {
            if (it.name == cipherName) return it
        }
        // TODO: 17.10.2021 throw CipherNotFoundException
        return listOfAllCiphers[0]
    }
}
