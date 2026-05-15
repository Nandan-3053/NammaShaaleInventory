package com.example.nammashaaleinventory.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nammashaaleinventory.ui.components.BottomNavBar
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {

    Scaffold(

        bottomBar = {
            BottomNavBar(navController)
        },

        topBar = {

            TopAppBar(
                title = {
                    Text("Settings")
                }
            )
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            SettingsCard(
                icon = {
                    Icon(Icons.Default.School, contentDescription = null)
                },
                title = "School Name",
                subtitle = "Namma Shaale"
            )

            Spacer(modifier = Modifier.height(12.dp))

            SettingsCard(
                icon = {
                    Icon(Icons.Default.Settings, contentDescription = null)
                },
                title = "Theme",
                subtitle = "Light Mode"
            )

            Spacer(modifier = Modifier.height(12.dp))

            SettingsCard(
                icon = {
                    Icon(Icons.Default.Info, contentDescription = null)
                },
                title = "App Version",
                subtitle = "1.0.0"
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {

                    FirebaseAuth.getInstance().signOut()

                    navController.navigate("login") {

                        popUpTo("dashboard") {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp)
            ) {

                Icon(
                    Icons.Default.Logout,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text("Logout")
            }
        }
    }
}

@Composable
fun SettingsCard(
    icon: @Composable () -> Unit,
    title: String,
    subtitle: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            icon()

            Column {

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}