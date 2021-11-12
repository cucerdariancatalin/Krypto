package com.vitassalvantes.krypto.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CorrespondenceDao {
    @Delete
    suspend fun delete(correspondence: Correspondence)

    @Insert
    suspend fun insert(correspondence: Correspondence)

    @Query("SELECT * FROM correspondence")
    fun getAllCorrespondences(): Flow<List<Correspondence>>
}