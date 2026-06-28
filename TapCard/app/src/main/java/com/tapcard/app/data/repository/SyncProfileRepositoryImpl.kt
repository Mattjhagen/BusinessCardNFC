package com.tapcard.app.data.repository

import com.tapcard.app.data.local.ProfileDao
import com.tapcard.app.data.local.ProfileEntity
import com.tapcard.app.data.remote.RemoteProfileDto
import com.tapcard.app.di.SupabaseClientProvider
import com.tapcard.app.domain.model.Profile
import com.tapcard.app.domain.model.SyncStatus
import com.tapcard.app.domain.repository.ProfileRepository
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SyncProfileRepositoryImpl @Inject constructor(
    private val profileDao: ProfileDao
) : ProfileRepository {

    private val client = SupabaseClientProvider.client
    
    private val _syncStatus = MutableStateFlow(SyncStatus.SAVED_LOCALLY)
    override val syncStatus: Flow<SyncStatus> = _syncStatus.asStateFlow()

    override fun getProfileFlow(): Flow<Profile?> {
        return profileDao.getProfileFlow().map { it?.toDomainModel() }
    }

    override suspend fun getProfile(): Profile? {
        return profileDao.getProfile()?.toDomainModel()
    }

    override suspend fun saveProfile(profile: Profile) {
        val entity = profile.toEntity().copy(isPendingSync = true)
        
        // 1. Save locally to Room first
        profileDao.saveProfile(entity)
        _syncStatus.value = SyncStatus.SAVED_LOCALLY

        // 2. If signed in and Supabase configured, sync to Supabase
        if (client == null) {
            _syncStatus.value = SyncStatus.SIGN_IN_TO_SYNC
            return
        }

        val session = client.auth.currentSessionOrNull()
        if (session == null) {
            _syncStatus.value = SyncStatus.SIGN_IN_TO_SYNC
            return
        }

        _syncStatus.value = SyncStatus.SYNCING

        try {
            val remoteDto = RemoteProfileDto(
                id = session.user?.id ?: return,
                username = profile.username,
                fullName = profile.fullName,
                jobTitle = profile.jobTitle,
                company = profile.company,
                phone = profile.phone,
                email = profile.email,
                website = profile.website,
                themeColorHex = profile.themeColorHex,
                isDarkTheme = profile.isDarkTheme,
                isPublic = true
            )

            client.postgrest["profiles"].upsert(remoteDto)
            
            // Mark as synced locally
            profileDao.saveProfile(entity.copy(isPendingSync = false))
            _syncStatus.value = SyncStatus.SYNCED
        } catch (e: Exception) {
            e.printStackTrace()
            // 3. If offline or error, keep local data and mark as pending sync
            _syncStatus.value = SyncStatus.SYNC_FAILED
        }
    }

    override suspend fun validateUsernameUniqueness(username: String): Boolean {
        if (client == null) return true // Cannot validate offline, assume true or false depending on product needs. For now, we allow offline.
        
        return try {
            val count = client.postgrest["profiles"]
                .select(columns = Columns.list("id")) {
                    filter {
                        eq("username", username)
                    }
                }.data.length
            // If data is just a JSON string, we should parse it. 
            // In postgrest-kt, select returns a PostgrestResult.
            val result = client.postgrest["profiles"]
                .select(columns = Columns.list("id")) {
                    filter {
                        eq("username", username)
                    }
                }.decodeList<RemoteProfileDto>()
            
            result.isEmpty()
        } catch (e: Exception) {
            e.printStackTrace()
            true // Allow local save if network fails
        }
    }

    private fun ProfileEntity.toDomainModel() = Profile(
        id = id,
        fullName = fullName,
        jobTitle = jobTitle,
        company = company,
        phone = phone,
        email = email,
        website = website,
        username = username,
        themeColorHex = themeColorHex,
        isDarkTheme = isDarkTheme,
        isPendingSync = isPendingSync
    )

    private fun Profile.toEntity() = ProfileEntity(
        id = id.ifBlank { "local_profile" },
        fullName = fullName,
        jobTitle = jobTitle,
        company = company,
        phone = phone,
        email = email,
        website = website,
        username = username,
        themeColorHex = themeColorHex,
        isDarkTheme = isDarkTheme,
        isPendingSync = isPendingSync
    )
}
