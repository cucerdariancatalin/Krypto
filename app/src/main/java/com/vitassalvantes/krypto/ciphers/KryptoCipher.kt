package com.vitassalvantes.krypto.ciphers

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * A cipher with a [name], [description] and methods to [encrypt] and [decrypt].
 */
abstract class KryptoCipher(
    /**
     * Name of the cipher. It should be in the string resources in a file with a name
     * corresponding to the English version of the cipher name.
     */
    @StringRes val name: Int,

    /**
     * Description of the cipher. It should be in the string resources in a file with a name
     * corresponding to the English version of the cipher name.
     */
    @StringRes val description: Int,

    /**
     * Cipher vector icon. Used when displaying cipher information.
     */
    @DrawableRes val icon: Int
) {

    /**
     * This method encrypts a [message] with the help of a [key].
     */
    abstract fun encrypt(message: String, key: String): String

    /**
     * This method decrypts a [message] with the help of a [key].
     */
    abstract fun decrypt(message: String, key: String): String
}