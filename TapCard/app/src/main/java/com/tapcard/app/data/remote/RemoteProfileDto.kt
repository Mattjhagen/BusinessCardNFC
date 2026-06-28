package com.tapcard.app.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteProfileDto(
    @SerialName("id")
    val id: String,
    
    @SerialName("username")
    val username: String,
    
    @SerialName("full_name")
    val fullName: String?,
    
    @SerialName("job_title")
    val jobTitle: String?,
    
    @SerialName("company")
    val company: String?,
    
    @SerialName("phone")
    val phone: String?,
    
    @SerialName("email")
    val email: String?,
    
    @SerialName("website")
    val website: String?,
    
    @SerialName("theme_color_hex")
    val themeColorHex: String?,
    
    @SerialName("is_dark_theme")
    val isDarkTheme: Boolean?,
    
    @SerialName("is_public")
    val isPublic: Boolean?
)
