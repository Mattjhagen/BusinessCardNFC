package com.tapcard.app.utils

import android.app.Activity
import android.content.Context
import android.nfc.FormatException
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.nfc.tech.NdefFormatable
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
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

    /**
     * Starts listening for physical NFC tags using ReaderMode.
     * When a tag is tapped, the provided callback will be executed.
     */
    fun startTagProgramming(activity: Activity, url: String, onResult: (Boolean, String) -> Unit): Boolean {
        if (getNfcState() != NfcState.READY) return false
        
        return try {
            val message = createNdefUrlMessage(url)
            val flags = NfcAdapter.FLAG_READER_NFC_A or 
                        NfcAdapter.FLAG_READER_NFC_B or 
                        NfcAdapter.FLAG_READER_NFC_F or 
                        NfcAdapter.FLAG_READER_NFC_V
            
            nfcAdapter?.enableReaderMode(activity, { tag ->
                writeNdefToTag(tag, message, onResult)
            }, flags, null)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun stopTagProgramming(activity: Activity) {
        try {
            nfcAdapter?.disableReaderMode(activity)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun writeNdefToTag(tag: Tag, message: NdefMessage, onResult: (Boolean, String) -> Unit) {
        try {
            val ndef = Ndef.get(tag)
            if (ndef != null) {
                ndef.connect()
                if (!ndef.isWritable) {
                    onResult(false, "NFC Tag is read-only.")
                    return
                }
                if (ndef.maxSize < message.toByteArray().size) {
                    onResult(false, "NFC Tag capacity is too small.")
                    return
                }
                ndef.writeNdefMessage(message)
                onResult(true, "Tag programmed successfully!")
            } else {
                val ndefFormatable = NdefFormatable.get(tag)
                if (ndefFormatable != null) {
                    ndefFormatable.connect()
                    ndefFormatable.format(message)
                    onResult(true, "Tag formatted and programmed successfully!")
                } else {
                    onResult(false, "NFC Tag is not NDEF formatable.")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            onResult(false, "Failed to write to NFC tag.")
        }
    }
}
