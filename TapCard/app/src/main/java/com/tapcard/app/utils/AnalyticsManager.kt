package com.tapcard.app.utils

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

object AnalyticsManager {
    private var analytics: FirebaseAnalytics? = null

    fun initialize(firebaseAnalytics: FirebaseAnalytics) {
        analytics = firebaseAnalytics
    }

    fun logEvent(eventName: String, params: Bundle? = null) {
        try {
            val safeEventName = eventName.replace(" ", "_").lowercase()
            analytics?.logEvent(safeEventName, params)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Specific events
    fun logAccountCreated() = logEvent("account_created")
    fun logCardSaved() = logEvent("card_saved")
    fun logQrGenerated() = logEvent("qr_generated")
    fun logQrShared() = logEvent("qr_shared")
    fun logNfcProgrammed() = logEvent("nfc_programmed")
    fun logImageUploaded() = logEvent("image_uploaded")
    fun logSyncCompleted() = logEvent("sync_completed")
    fun logWalletButtonTapped() = logEvent("wallet_button_tapped")
}
