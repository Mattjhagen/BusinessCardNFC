package com.tapcard.app.repository

import com.tapcard.app.data.models.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getProfileFlow(): Flow<Profile?>
    suspend fun getProfile(): Profile?
    suspend fun saveProfile(profile: Profile)
}
