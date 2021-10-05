package com.vitassalvantes.krypto.ciphers

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

abstract class KryptoCipher(
    val name: String,
    @StringRes val description: Int,
    val key: Any,
    val icon: ImageVector
) {

    abstract fun encrypt(message: String): String

    abstract fun decrypt(message: String): String
}