package com.vitassalvantes.krypto.ciphers

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.ui.graphics.vector.ImageVector
import com.vitassalvantes.krypto.R

class CaesarCipher(
    name: String = "Caesar Cipher",
    description: Int = R.string.caesar_cipher_description,
    key: Int = 0,
    icon: ImageVector = Icons.Filled.Edit
) : KryptoCipher(name = name, description = description, key = key, icon = icon) {

    override fun encrypt(message: String): String {
        TODO("Not yet implemented")
    }

    override fun decrypt(message: String): String {
        TODO("Not yet implemented")
    }
}