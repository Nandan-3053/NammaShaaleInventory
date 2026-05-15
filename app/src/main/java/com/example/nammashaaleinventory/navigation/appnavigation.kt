package com.example.nammashaaleinventory.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nammashaaleinventory.ui.screens.AddAssetScreen
import com.example.nammashaaleinventory.ui.screens.AssetListScreen
import com.example.nammashaaleinventory.ui.screens.DashboardScreen
import com.example.nammashaaleinventory.ui.screens.EditAssetScreen
import com.example.nammashaaleinventory.ui.screens.LoginScreen
import com.example.nammashaaleinventory.ui.screens.SettingsScreen
import com.example.nammashaaleinventory.ui.screens.SignupScreen
import com.example.nammashaaleinventory.ui.screens.SplashScreen

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

        composable("signup") {
            SignupScreen(navController)
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

        composable("settings") {
            SettingsScreen(navController)
        }

        composable(
            "edit_asset/{assetId}/{name}/{category}/{serial}/{location}/{notes}/{status}"
        ) { backStackEntry ->

            EditAssetScreen(
                navController = navController,

                assetId = backStackEntry.arguments
                    ?.getString("assetId") ?: "",

                name = backStackEntry.arguments
                    ?.getString("name") ?: "",

                category = backStackEntry.arguments
                    ?.getString("category") ?: "",

                serial = backStackEntry.arguments
                    ?.getString("serial") ?: "",

                location = backStackEntry.arguments
                    ?.getString("location") ?: "",

                notes = backStackEntry.arguments
                    ?.getString("notes") ?: "",

                status = backStackEntry.arguments
                    ?.getString("status") ?: ""
            )
        }
    }
}