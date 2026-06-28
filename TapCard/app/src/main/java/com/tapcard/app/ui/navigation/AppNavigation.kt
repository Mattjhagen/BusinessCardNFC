package com.tapcard.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tapcard.app.ui.screens.DashboardScreen
import com.tapcard.app.ui.screens.OnboardingScreen
import com.tapcard.app.ui.screens.EditorScreen
import com.tapcard.app.ui.screens.AuthScreen
import com.tapcard.app.ui.screens.SettingsScreen
import com.tapcard.app.ui.screens.FeedbackScreen
import com.tapcard.app.ui.screens.DeveloperSettingsScreen
import com.tapcard.app.ui.viewmodel.ProfileViewModel

@Composable
fun AppNavigation(sharedViewModel: ProfileViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "auth") {
        composable("auth") {
            AuthScreen(
                onAuthSuccess = {
                    navController.navigate("onboarding") { popUpTo("auth") { inclusive = true } }
                }
            )
        }
        composable("onboarding") {
            OnboardingScreen(
                viewModel = sharedViewModel,
                onComplete = { navController.navigate("dashboard") { popUpTo("onboarding") { inclusive = true } } }
            )
        }
        composable("dashboard") {
            DashboardScreen(
                viewModel = sharedViewModel,
                onEditCard = { navController.navigate("editor") },
                onNavigateToSettings = { navController.navigate("settings") }
            )
        }
        composable("editor") {
            EditorScreen(
                viewModel = sharedViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable("settings") {
            SettingsScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToFeedback = { navController.navigate("feedback") },
                onNavigateToDeveloperSettings = { navController.navigate("developer_settings") }
            )
        }
        composable("feedback") {
            FeedbackScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable("developer_settings") {
            DeveloperSettingsScreen(
                viewModel = sharedViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
