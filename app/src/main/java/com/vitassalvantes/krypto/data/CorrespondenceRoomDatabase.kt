package com.vitassalvantes.krypto.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Correspondence::class], version = 1, exportSchema = false)
abstract class CorrespondenceRoomDatabase : RoomDatabase() {

    abstract fun correspondenceDao(): CorrespondenceDao

    companion object {
        @Volatile
        private var INSTANCE: CorrespondenceRoomDatabase? = null

        fun getDatabase(context: Context): CorrespondenceRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CorrespondenceRoomDatabase::class.java,
                    "correspondence_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}