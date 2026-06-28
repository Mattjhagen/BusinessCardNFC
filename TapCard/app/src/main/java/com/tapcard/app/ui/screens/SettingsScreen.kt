package com.tapcard.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.tapcard.app.BuildConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    onNavigateToFeedback: () -> Unit,
    onNavigateToDeveloperSettings: () -> Unit
) {
    var versionTapCount by remember { mutableStateOf(0) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Account",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            ListItem(
                headlineContent = { Text("Profile Sync Status") },
                supportingContent = { Text("Check cloud synchronization status") }
            )
            Divider()

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "App Details",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            ListItem(
                headlineContent = { Text("Send Feedback") },
                supportingContent = { Text("Report a bug or suggest a feature") },
                modifier = Modifier.clickable { onNavigateToFeedback() }
            )
            Divider()
            ListItem(
                headlineContent = { Text("About TapCard") },
                supportingContent = { Text("Version ${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})") },
                modifier = Modifier.clickable {
                    versionTapCount++
                    if (versionTapCount >= 7) {
                        versionTapCount = 0
                        onNavigateToDeveloperSettings()
                    }
                }
            )
            Divider()
            ListItem(
                headlineContent = { Text("Open Source Licenses") }
            )
            Divider()
            ListItem(
                headlineContent = { Text("Privacy Policy") }
            )
        }
    }
}
