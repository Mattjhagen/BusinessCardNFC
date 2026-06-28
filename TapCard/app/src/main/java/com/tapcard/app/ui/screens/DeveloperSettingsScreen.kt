package com.tapcard.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tapcard.app.BuildConfig
import com.tapcard.app.ui.viewmodel.ProfileViewModel
import com.tapcard.app.di.SupabaseClientProvider
import io.github.jan.supabase.gotrue.auth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeveloperSettingsScreen(
    viewModel: ProfileViewModel,
    onNavigateBack: () -> Unit
) {
    val syncStatus by viewModel.syncStatus.collectAsStateWithLifecycle()
    val client = SupabaseClientProvider.client
    val authStatus = client?.auth?.currentSessionOrNull() != null
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Developer Settings", color = MaterialTheme.colorScheme.error) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Text("←")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text("Beta Diagnostics", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))

            DiagnosticItem("Build Number", BuildConfig.VERSION_CODE.toString())
            DiagnosticItem("Version Name", BuildConfig.VERSION_NAME)
            DiagnosticItem("Build Type", BuildConfig.BUILD_TYPE)
            DiagnosticItem("Application ID", BuildConfig.APPLICATION_ID)
            
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            
            DiagnosticItem("Supabase Configured", (client != null).toString())
            DiagnosticItem("Auth Status", if (authStatus) "Signed In" else "Signed Out")
            DiagnosticItem("Sync Status", syncStatus.name)
            
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            
            DiagnosticItem("Google Wallet Enabled", com.tapcard.app.domain.wallet.WalletConfig.isGoogleWalletEnabled.toString())

            Spacer(modifier = Modifier.height(32.dp))
            
            Button(
                onClick = { /* Export logs implementation */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Export Diagnostics")
            }
        }
    }
}

@Composable
fun DiagnosticItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, fontWeight = FontWeight.SemiBold)
        Text(value, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
