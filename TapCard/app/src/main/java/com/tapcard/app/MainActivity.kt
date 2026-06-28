package com.tapcard.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapcard.app.ui.navigation.AppNavigation
import com.tapcard.app.ui.theme.TapCardTheme
import com.tapcard.app.ui.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.tapcard.app.utils.AnalyticsManager

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            AnalyticsManager.initialize(Firebase.analytics)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        setContent {
            val sharedViewModel: ProfileViewModel = hiltViewModel()
            
            TapCardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(sharedViewModel)
                }
            }
        }
    }
}
