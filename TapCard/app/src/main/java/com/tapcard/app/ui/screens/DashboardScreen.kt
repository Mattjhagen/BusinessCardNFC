package com.tapcard.app.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import android.content.Intent
import com.tapcard.app.ui.components.BusinessCardPreview
import com.tapcard.app.utils.QRCodeGenerator
import com.tapcard.app.ui.viewmodel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: ProfileViewModel,
    onEditCard: () -> Unit
) {
    val profile by viewModel.profileState.collectAsState()
    val shareUrl = viewModel.getShareableUrl()
    val context = LocalContext.current

    val clipboardManager = LocalClipboardManager.current

    // Generate high-resolution QR Code bitmap (1024x1024) for both display and export
    val qrCodeBitmap = remember(shareUrl) {
        QRCodeGenerator.generateQRCode(shareUrl, size = 1024)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard", fontWeight = FontWeight.Bold) },
                actions = {
                    TextButton(onClick = onEditCard) {
                        Text("Edit", fontWeight = FontWeight.Bold)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Card Preview
            BusinessCardPreview(profile = profile)

            Spacer(modifier = Modifier.height(32.dp))

            // QR Code
            Text(
                text = "Share via QR Code",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            qrCodeBitmap?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "QR Code",
                    modifier = Modifier.size(200.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Action Buttons for QR and Link
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedButton(onClick = {
                    qrCodeBitmap?.let { bitmap ->
                        viewModel.shareQrCode(bitmap, "Here is my digital business card: $shareUrl")?.let { intent ->
                            context.startActivity(Intent.createChooser(intent, "Share QR Code"))
                        }
                    }
                }) {
                    Text("Share QR")
                }

                OutlinedButton(onClick = {
                    qrCodeBitmap?.let { bitmap ->
                        val saved = viewModel.saveQrToGallery(bitmap)
                        if (saved) {
                            Toast.makeText(context, "Saved to gallery", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Failed to save", Toast.LENGTH_SHORT).show()
                        }
                    }
                }) {
                    Text("Save QR")
                }

                OutlinedButton(onClick = {
                    clipboardManager.setText(AnnotatedString(shareUrl))
                    Toast.makeText(context, "Link copied!", Toast.LENGTH_SHORT).show()
                }) {
                    Text("Copy Link")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // NFC Button
            Button(
                onClick = {
                    // TODO: Implement actual NFC NdefMessage broadcast
                    Toast.makeText(context, "NFC broadcast started for $shareUrl (Mock)", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Share via NFC", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Google Wallet Button
            OutlinedButton(
                onClick = {
                    // TODO: Implement Google Wallet API PayClient savePassesJwt
                    Toast.makeText(context, "Requesting Wallet JWT from server... (Mock)", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Add to Google Wallet", fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
