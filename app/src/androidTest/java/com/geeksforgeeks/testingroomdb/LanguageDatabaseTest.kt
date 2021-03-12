package com.geeksforgeeks.testingroomdb

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class) // Annotate with @RunWith
class LanguageDatabaseTest : TestCase() {
    // get reference to the LanguageDatabase and LanguageDao class
    private lateinit var db: LanguageDatabase
    private lateinit var dao: LanguageDao

    // Override function setUp() and annotate it with @Before
    // this function will be called at first when this test class is called
    @Before
    public override fun setUp() {
        // get context -- since this is an instrumental test it requires context from the running application
        val context = ApplicationProvider.getApplicationContext<Context>()
        // initialize the db and dao variable
        db = Room.inMemoryDatabaseBuilder(context, LanguageDatabase::class.java).build()
        dao = db.getLanguageDao()
    }

    // Override function closeDb() and annotate it with @After
    // this function will be called at last when this test class is called
    @After
    fun closeDb() {
        db.close()
    }

    // create a test function and annotate it with @Test
    // here we are first adding an item to the db and then checking if that item
    // is present in the db -- if the item is present then our test cases pass
    @Test
    fun writeAndReadLanguage() = runBlocking {
        val language = Language("Java", "2 Years")
        dao.addLanguage(language)
        val languages = dao.getAllLanguages()
        assertThat(languages.contains(language)).isTrue()
    }

}