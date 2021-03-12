package com.geeksforgeeks.testingroomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Language::class] , version = 1)
abstract class LanguageDatabase : RoomDatabase() {

    // get reference of the dao interface that we just created
    abstract fun getLanguageDao() : LanguageDao

    companion object{
        private const val DB_NAME = "Language-Database.db"

        // Get reference of the LanguageDatabase and assign it null value
        @Volatile
        private var instance : LanguageDatabase? = null
        private val LOCK = Any()

        // create an operator fun which has context as a parameter
        // assign value to the instance variable
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also{
                instance = it
            }
        }
        // create a buildDatabase function assign the required values
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            LanguageDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration().build()

    }
}