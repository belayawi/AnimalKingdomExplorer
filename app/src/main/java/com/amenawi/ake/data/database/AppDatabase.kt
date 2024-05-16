package com.amenawi.ake.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amenawi.ake.data.dao.AnimalDao
import com.amenawi.ake.data.dao.SpeciesDao
import com.amenawi.ake.data.model.*


@Database(entities = [Animal::class, Species::class], version=1)
abstract class AppDatabase : RoomDatabase() {
    abstract  fun animalDao():AnimalDao
    abstract fun speciesDao():SpeciesDao
    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // If the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "animal_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}


