package com.tapcard.app.data.repository

import com.tapcard.app.data.local.ProfileDao
import com.tapcard.app.data.local.ProfileEntity
import com.tapcard.app.domain.model.SyncStatus
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import android.content.Context

class SyncProfileRepositoryImplTest {

    private lateinit var repository: SyncProfileRepositoryImpl
    private val profileDao: ProfileDao = mockk()
    private val context: Context = mockk()

    @Before
    fun setup() {
        coEvery { context.getSharedPreferences(any(), any()) } returns mockk(relaxed = true)
        repository = SyncProfileRepositoryImpl(profileDao, context)
    }

    @Test
    fun testOfflineSave_SetsPendingSync() = runTest {
        val testEntity = ProfileEntity(id = "1", isPendingSync = true)
        
        coEvery { profileDao.getAllProfilesFlow() } returns flowOf(listOf(testEntity))
        coEvery { profileDao.saveProfile(any()) } returns Unit

        repository.getProfileFlow().first() // Initial load
        
        // Since we are not authenticated in tests, saveProfile goes into offline mode
        val profile = com.tapcard.app.domain.model.Profile(username = "offline_user")
        repository.saveProfile(profile)

        val syncStatus = repository.syncStatus.first()
        assertEquals(SyncStatus.SIGN_IN_TO_SYNC, syncStatus)
    }
}
