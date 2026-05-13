package com.example.nammashaaleinventory.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nammashaaleinventory.ui.components.BottomNavBar

@Composable
fun DashboardScreen(navController: NavController) {

    Scaffold(

        bottomBar = {
            BottomNavBar(navController)
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                text = "Dashboard",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            DashboardCard("Total Assets", "50")

            DashboardCard("Working", "40")

            DashboardCard("Needs Repair", "7")

            DashboardCard("Broken", "3")

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    navController.navigate("assets")
                },
                modifier = Modifier.fillMaxWidth()
            ) {

                Text("View Assets")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    navController.navigate("add_asset")
                },
                modifier = Modifier.fillMaxWidth()
            ) {

                Text("Add Asset")
            }
        }
    }
}

@Composable
fun DashboardCard(title: String, value: String) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(text = title)

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}