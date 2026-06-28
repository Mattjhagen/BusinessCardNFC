package com.tapcard.app.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProfileDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: ProfileDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.profileDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertAndGetProfile() = runTest {
        val entity = ProfileEntity(
            id = "1",
            fullName = "Test User",
            username = "testuser",
            jobTitle = "Developer",
            company = "Tech Inc",
            phone = "123",
            email = "test@example.com",
            website = "test.com",
            isPendingSync = false
        )
        
        dao.saveProfile(entity)
        
        val loaded = dao.getProfileFlow().first()
        assertEquals(entity.fullName, loaded?.fullName)
        assertEquals(entity.username, loaded?.username)
    }
}
