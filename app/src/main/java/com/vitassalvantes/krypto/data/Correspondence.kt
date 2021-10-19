package com.vitassalvantes.krypto.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "correspondence")
data class Correspondence(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "correspondence_name")
    val correspondenceName: String,
    @ColumnInfo(name = "cipher_name")
    val cipherName: Int,
    @ColumnInfo(name = "key")
    val key: String
)
