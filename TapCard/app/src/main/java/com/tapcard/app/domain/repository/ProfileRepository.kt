package com.tapcard.app.domain.repository

import com.tapcard.app.domain.model.Profile
import kotlinx.coroutines.flow.Flow

import com.tapcard.app.domain.model.SyncStatus

interface ProfileRepository {
    fun getProfileFlow(): Flow<Profile?>
    fun getAllProfilesFlow(): Flow<List<Profile>>
    suspend fun getProfile(): Profile?
    suspend fun saveProfile(profile: Profile)
    fun setActiveProfileId(id: String)
    val activeProfileIdFlow: Flow<String?>
    
    val syncStatus: Flow<SyncStatus>
    val syncError: Flow<String?>
    suspend fun validateUsernameUniqueness(username: String): Boolean
}
