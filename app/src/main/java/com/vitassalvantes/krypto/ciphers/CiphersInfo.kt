package com.vitassalvantes.krypto.ciphers

/**
 * Singleton to access the list of all implemented ciphers
 */
object CiphersInfo {
    /**
     * List of ciphers used in the app
     */
    val listOfAllCiphers = listOf<KryptoCipher>(
        CaesarCipher(),
        CaesarCipher(name = "Caesar 1"),
        CaesarCipher(name = "Caesar 2")
    )
}
