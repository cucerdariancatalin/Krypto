package com.vitassalvantes.krypto.ciphers

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.ui.graphics.vector.ImageVector
import com.vitassalvantes.krypto.R

/**
 * Class for encrypting messages using the Caesar cipher
 */
class CaesarCipher(
    name: Int = R.string.caesar_cipher_name,
    description: Int = R.string.caesar_cipher_description,
    icon: ImageVector = Icons.Filled.Edit
) : KryptoCipher(name = name, description = description, icon = icon) {

    override fun encrypt(message: String, key: String): String {
        val keyNumber = keyToInt(key)

        //First, the string is converted to a character array.
        //Then a key is added to the Unicode number of each element in the array.
        //The resulting array is converted back to a string.
        return message.toCharArray().map { it.plus(keyNumber) }.joinToString(separator = "")
    }

    override fun decrypt(message: String, key: String): String {
        val keyNumber = keyToInt(key)

        //First, the string is converted to a character array.
        //Then a key is subtracted from the Unicode number of each element in the array.
        //The resulting array is converted back to a string.
        return message.toCharArray().map { it.minus(keyNumber) }.joinToString(separator = "")
    }

    /**
     * For this cipher, the [key] must be converted to Int.
     */
    private fun keyToInt(key: String): Int {
        var keyNumber = 0

        //First, the string is converted to a character array.
        //Then the Unicode number of each element in the array is added to keyNumber.
        //The resulting array is converted back to a string.
        key.toCharArray().forEach { keyNumber += it.code }
        return keyNumber
    }
}