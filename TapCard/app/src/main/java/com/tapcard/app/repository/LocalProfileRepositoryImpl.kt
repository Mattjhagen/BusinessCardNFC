package com.tapcard.app.repository

import com.tapcard.app.data.models.Profile
import com.tapcard.app.database.ProfileDao
import com.tapcard.app.database.ProfileEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalProfileRepositoryImpl(private val profileDao: ProfileDao) : ProfileRepository {

    override fun getProfileFlow(): Flow<Profile?> {
        return profileDao.getProfileFlow().map { it?.toDomainModel() }
    }

    override suspend fun getProfile(): Profile? {
        return profileDao.getProfile()?.toDomainModel()
    }

    override suspend fun saveProfile(profile: Profile) {
        profileDao.saveProfile(profile.toEntity())
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
        isDarkTheme = isDarkTheme
    )

    private fun Profile.toEntity() = ProfileEntity(
        fullName = fullName,
        jobTitle = jobTitle,
        company = company,
        phone = phone,
        email = email,
        website = website,
        username = username,
        themeColorHex = themeColorHex,
        isDarkTheme = isDarkTheme
    )
}
