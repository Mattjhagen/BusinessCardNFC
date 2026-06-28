package com.tapcard.app.utils

import android.app.Activity
import android.content.Context
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

enum class NfcState {
    UNAVAILABLE,
    DISABLED,
    READY
}

@Singleton
class NfcService @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val nfcAdapter: NfcAdapter? by lazy {
        NfcAdapter.getDefaultAdapter(context)
    }

    fun getNfcState(): NfcState {
        return when {
            nfcAdapter == null -> NfcState.UNAVAILABLE
            !nfcAdapter!!.isEnabled -> NfcState.DISABLED
            else -> NfcState.READY
        }
    }

    fun createNdefUrlMessage(url: String): NdefMessage {
        val record = NdefRecord.createUri(url)
        return NdefMessage(arrayOf(record))
    }

    // Android Beam (deprecated in API 29, but requested as 'Beam-style' fallback)
    @Suppress("DEPRECATION")
    fun startSharing(activity: Activity, url: String): Boolean {
        if (getNfcState() != NfcState.READY) return false
        
        return try {
            val message = createNdefUrlMessage(url)
            // setNdefPushMessage was removed in API 34, so we must use reflection to call it on older devices
            val method = nfcAdapter?.javaClass?.getMethod("setNdefPushMessage", NdefMessage::class.java, Activity::class.java, Array<Activity>::class.java)
            method?.invoke(nfcAdapter, message, activity, emptyArray<Activity>())
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    @Suppress("DEPRECATION")
    fun stopSharing(activity: Activity) {
        try {
            val method = nfcAdapter?.javaClass?.getMethod("setNdefPushMessage", NdefMessage::class.java, Activity::class.java, Array<Activity>::class.java)
            method?.invoke(nfcAdapter, null, activity, emptyArray<Activity>())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
