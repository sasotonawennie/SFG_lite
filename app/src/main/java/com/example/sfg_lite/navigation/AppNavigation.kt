package com.example.sfg_lite.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sfg_lite.ui.LandingScreen
import com.example.sfg_lite.ui.LoadingScreen

sealed class Screen(val route: String) {
    object Loading : Screen("loading")
    object Landing : Screen("landing")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Screen.Loading.route
    ) {
        composable(Screen.Loading.route) {
            LoadingScreen(
                onLoadingFinished = {
                    navController.navigate(Screen.Landing.route) {
                        popUpTo(Screen.Loading.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Landing.route) {
            LandingScreen()
        }
    }
}
