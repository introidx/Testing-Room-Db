package com.geeksforgeeks.testingroomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LanguageDao {
   // Write two functions one for adding language to the database
   // and another for retrieving all the items present in room db.
    @Insert
    suspend fun addLanguage(language: Language)

    @Query("SELECT * FROM language ORDER BY languageName DESC")
    suspend fun getAllLanguages(): List<Language>
}