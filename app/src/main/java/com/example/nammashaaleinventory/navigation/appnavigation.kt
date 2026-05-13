package com.example.nammashaaleinventory.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nammashaaleinventory.ui.screens.AssetListScreen
import com.example.nammashaaleinventory.ui.screens.DashboardScreen
import com.example.nammashaaleinventory.ui.screens.LoginScreen
import com.example.nammashaaleinventory.ui.screens.SplashScreen
import com.example.nammashaaleinventory.ui.screens.AddAssetScreen
@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {

        composable("splash") {
            SplashScreen(navController)
        }

        composable("login") {
            LoginScreen(navController)
        }

        composable("dashboard") {
            DashboardScreen(navController)
        }

        composable("assets") {
            AssetListScreen(navController)
        }
        composable("add_asset") {
            AddAssetScreen(navController)
        }
    }
}