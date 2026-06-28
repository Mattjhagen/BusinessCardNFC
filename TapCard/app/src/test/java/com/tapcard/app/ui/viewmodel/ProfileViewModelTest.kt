package com.tapcard.app.ui.viewmodel

import com.tapcard.app.domain.model.Profile
import com.tapcard.app.domain.repository.ProfileRepository
import com.tapcard.app.utils.QRExportService
import com.tapcard.app.utils.NfcService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileViewModelTest {

    private lateinit var viewModel: ProfileViewModel
    private val profileRepository: ProfileRepository = mockk()
    private val nfcService: NfcService = mockk()
    private val qrExportService: QRExportService = mockk()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        coEvery { profileRepository.getProfileFlow() } returns MutableStateFlow(Profile(username = "testuser"))
        coEvery { profileRepository.getAllProfilesFlow() } returns MutableStateFlow(listOf(Profile(username = "testuser")))
        coEvery { profileRepository.syncStatus } returns MutableStateFlow(com.tapcard.app.domain.model.SyncStatus.SYNCED)
        coEvery { profileRepository.syncError } returns MutableStateFlow(null)
        coEvery { nfcService.getNfcState() } returns com.tapcard.app.utils.NfcState.READY

        viewModel = ProfileViewModel(profileRepository, qrExportService, nfcService)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testUsernameValidation_Valid() = runTest {
        coEvery { profileRepository.validateUsernameUniqueness("newuser") } returns true
        
        viewModel.onUsernameChanged("newuser")
        testDispatcher.scheduler.advanceTimeBy(1000)
        
        assertEquals(UsernameValidationState.AVAILABLE, viewModel.usernameValidationState.value)
    }

    @Test
    fun testUsernameValidation_InvalidFormat() = runTest {
        viewModel.onUsernameChanged("In V@lid")
        testDispatcher.scheduler.advanceTimeBy(1000)
        
        assertEquals(UsernameValidationState.INVALID_FORMAT, viewModel.usernameValidationState.value)
    }
}
