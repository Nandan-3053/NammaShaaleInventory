package com.example.nammashaaleinventory.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nammashaaleinventory.model.Asset
import com.example.nammashaaleinventory.ui.components.BottomNavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssetListScreen(navController: NavController) {

    var searchText by remember {
        mutableStateOf("")
    }

    val assetList = listOf(

        Asset(1, "Microscope", "MS101", "Lab", "Working"),

        Asset(2, "Football", "FB202", "Sports", "Broken"),

        Asset(3, "Tablet", "TB303", "Electronics", "Needs Repair")
    )

    val filteredList = assetList.filter {

        it.name.contains(searchText, ignoreCase = true)
    }

    Scaffold(

        bottomBar = {
            BottomNavBar(navController)
        },

        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    navController.navigate("add_asset")
                }
            ) {

                Text("+")
            }
        },

        topBar = {

            TopAppBar(
                title = {
                    Text("Assets")
                }
            )
        }

    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            item {

                OutlinedTextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                    },
                    label = {
                        Text("Search Assets")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
            }

            items(filteredList) { asset ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = asset.name,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text("Serial: ${asset.serialNumber}")

                        Text("Category: ${asset.category}")

                        Spacer(modifier = Modifier.height(8.dp))

                        ConditionBadge(asset.condition)
                    }
                }
            }
        }
    }
}

@Composable
fun ConditionBadge(condition: String) {

    val color = when (condition) {

        "Working" -> Color(0xFF4CAF50)

        "Needs Repair" -> Color(0xFFFFC107)

        else -> Color(0xFFF44336)
    }

    Box(
        modifier = Modifier
            .padding(top = 8.dp)
            .background(
                color = color,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {

        Text(
            text = condition,
            color = Color.White
        )
    }
}