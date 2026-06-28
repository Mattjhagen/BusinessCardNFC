package com.tapcard.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tapcard.app.domain.model.Profile
import com.tapcard.app.domain.model.SyncStatus
import com.tapcard.app.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.graphics.Bitmap
import android.content.Intent
import com.tapcard.app.utils.QRExportService
import com.tapcard.app.utils.NfcService
import com.tapcard.app.utils.NfcState
import android.app.Activity

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository,
    private val qrExportService: QRExportService,
    private val nfcService: NfcService
) : ViewModel() {
    private val _profileState = MutableStateFlow(Profile())
    val profileState: StateFlow<Profile> = _profileState.asStateFlow()
    
    private val _nfcState = MutableStateFlow(nfcService.getNfcState())
    val nfcState: StateFlow<NfcState> = _nfcState.asStateFlow()
    
    private val _nfcProgrammingResult = MutableSharedFlow<Pair<Boolean, String>>()
    val nfcProgrammingResult = _nfcProgrammingResult.asSharedFlow()

    private val _isSaved = MutableStateFlow(false)
    val isSaved = _isSaved.asStateFlow()

    val syncStatus: StateFlow<SyncStatus> = repository.syncStatus
        .stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
            initialValue = SyncStatus.SAVED_LOCALLY
        )

    init {
        viewModelScope.launch {
            repository.getProfileFlow().collect { profile ->
                if (profile != null) {
                    _profileState.value = profile
                }
            }
        }
    }

    fun updateProfile(newProfile: Profile) {
        _profileState.update { newProfile }
    }

    fun saveProfile() {
        viewModelScope.launch {
            repository.saveProfile(_profileState.value)
            _isSaved.value = true
        }
    }

    fun getShareableUrl(): String {
        val username = profileState.value.username.ifBlank { profileState.value.id.toString() }
        return "https://tapcard.app/card/$username"
    }

    suspend fun validateUsername(username: String): Boolean {
        return repository.validateUsernameUniqueness(username)
    }

    fun saveQrToGallery(bitmap: Bitmap): Boolean {
        return qrExportService.saveQrToGallery(bitmap)
    }

    fun shareQrCode(bitmap: Bitmap, text: String): Intent? {
        return qrExportService.shareQrCode(bitmap, text)
    }

    fun checkNfcState() {
        _nfcState.value = nfcService.getNfcState()
    }

    fun startNfcProgramming(activity: Activity): Boolean {
        checkNfcState()
        return nfcService.startTagProgramming(activity, getShareableUrl()) { success, message ->
            viewModelScope.launch {
                _nfcProgrammingResult.emit(Pair(success, message))
            }
        }
    }

    fun stopNfcProgramming(activity: Activity) {
        nfcService.stopTagProgramming(activity)
    }
}
