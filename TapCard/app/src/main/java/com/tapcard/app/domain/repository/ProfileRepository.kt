package com.tapcard.app.domain.repository

import com.tapcard.app.domain.model.Profile
import kotlinx.coroutines.flow.Flow

import com.tapcard.app.domain.model.SyncStatus

interface ProfileRepository {
    fun getProfileFlow(): Flow<Profile?>
    suspend fun getProfile(): Profile?
    suspend fun saveProfile(profile: Profile)
    
    val syncStatus: Flow<SyncStatus>
    suspend fun validateUsernameUniqueness(username: String): Boolean
}
