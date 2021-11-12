package com.vitassalvantes.krypto

import android.app.Application
import com.vitassalvantes.krypto.data.CorrespondenceRoomDatabase

class KryptoApplication : Application() {
    val database: CorrespondenceRoomDatabase by lazy { CorrespondenceRoomDatabase.getDatabase(this) }
}