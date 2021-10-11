package com.vitassalvantes.krypto.ciphers

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

abstract class KryptoCipher(
    val name: String,
    @StringRes val description: Int,
    val icon: ImageVector
) {

    abstract fun encrypt(message: String, key: String): String

    abstract fun decrypt(message: String, key: String): String
}