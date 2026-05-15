package com.example.nammashaaleinventory.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BottomNavBar(navController: NavController) {

    NavigationBar {

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("dashboard")
            },
            icon = {
                Icon(Icons.Default.Home, contentDescription = "Dashboard")
            },
            label = {
                Text("Home")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("assets")
            },
            icon = {
                Icon(Icons.Default.List, contentDescription = "Assets")
            },
            label = {
                Text("Assets")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("add_asset")
            },
            icon = {
                Icon(Icons.Default.Add, contentDescription = "Add")
            },
            label = {
                Text("Add")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("settings")
            },
            icon = {
                Icon(Icons.Default.Settings, contentDescription = "Settings")
            },
            label = {
                Text("Settings")
            }
        )
    }
}