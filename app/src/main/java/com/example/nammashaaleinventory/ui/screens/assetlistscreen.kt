package com.example.nammashaaleinventory.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nammashaaleinventory.model.AssetData
import com.example.nammashaaleinventory.ui.components.BottomNavBar
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssetListScreen(navController: NavController) {

    var searchText by remember {
        mutableStateOf("")
    }

    var selectedFilter by remember {
        mutableStateOf("All")
    }

    var assetList by remember {
        mutableStateOf(listOf<AssetData>())
    }

    val db = FirebaseFirestore.getInstance()

    LaunchedEffect(Unit) {

        db.collection("assets")
            .addSnapshotListener { value, error ->

                if (value != null) {

                    val assets = value.documents.mapNotNull {

                        it.toObject(AssetData::class.java)?.copy(
                            id = it.id
                        )
                    }

                    assetList = assets
                }
            }
    }

    val filteredList = assetList.filter {

        val matchesSearch = it.assetName.contains(
            searchText,
            ignoreCase = true
        )

        val matchesFilter = when (selectedFilter) {

            "Working" -> it.status == "Working"

            "Needs Repair" -> it.status == "Needs Repair"

            "Broken" -> it.status == "Broken"

            else -> true
        }

        matchesSearch && matchesFilter
    }

    Scaffold(

        bottomBar = {
            BottomNavBar(navController)
        },

        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    navController.navigate("add_asset")
                },
                containerColor = Color(0xFF1565C0)
            ) {

                Icon(
                    Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },

        topBar = {

            TopAppBar(
                title = {
                    Text(
                        text = "Assets",
                        fontWeight = FontWeight.Bold
                    )
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

            OutlinedTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                },
                leadingIcon = {

                    Icon(
                        Icons.Default.Search,
                        contentDescription = null
                    )
                },
                placeholder = {
                    Text("Search assets")
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                FilterChip(
                    selected = selectedFilter == "All",
                    onClick = {
                        selectedFilter = "All"
                    },
                    label = {
                        Text("All")
                    }
                )

                FilterChip(
                    selected = selectedFilter == "Working",
                    onClick = {
                        selectedFilter = "Working"
                    },
                    label = {
                        Text("Working")
                    }
                )

                FilterChip(
                    selected = selectedFilter == "Needs Repair",
                    onClick = {
                        selectedFilter = "Needs Repair"
                    },
                    label = {
                        Text("Repair")
                    }
                )

                FilterChip(
                    selected = selectedFilter == "Broken",
                    onClick = {
                        selectedFilter = "Broken"
                    },
                    label = {
                        Text("Broken")
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (filteredList.isEmpty()) {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(28.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "📦",
                            style = MaterialTheme.typography.displayMedium
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        Text(
                            text = "No Assets Found",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Try adding assets or changing filters.",
                            color = Color.Gray
                        )
                    }
                }

            } else {

                LazyColumn {

                    items(filteredList) { asset ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            shape = RoundedCornerShape(26.dp),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 4.dp
                            )
                        ) {

                            Column(
                                modifier = Modifier.padding(20.dp)
                            ) {

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Column {

                                        Text(
                                            text = asset.assetName,
                                            style = MaterialTheme.typography.titleLarge,
                                            fontWeight = FontWeight.Bold
                                        )

                                        Spacer(modifier = Modifier.height(4.dp))

                                        Text(
                                            text = asset.category,
                                            color = Color.Gray
                                        )
                                    }

                                    StatusBadge(asset.status)
                                }

                                Spacer(modifier = Modifier.height(18.dp))

                                AssetInfoRow(
                                    label = "Serial",
                                    value = asset.serialNumber
                                )

                                AssetInfoRow(
                                    label = "Location",
                                    value = asset.location
                                )

                                if (asset.notes.isNotEmpty()) {

                                    Spacer(modifier = Modifier.height(14.dp))

                                    Text(
                                        text = "Notes",
                                        fontWeight = FontWeight.SemiBold
                                    )

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(
                                        text = asset.notes,
                                        color = Color.Gray
                                    )
                                }

                                Spacer(modifier = Modifier.height(18.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {

                                    TextButton(
                                        onClick = {

                                            navController.navigate(

                                                "edit_asset/" +
                                                        "${asset.id}/" +
                                                        "${asset.assetName}/" +
                                                        "${asset.category}/" +
                                                        "${asset.serialNumber}/" +
                                                        "${asset.location}/" +
                                                        "${asset.notes}/" +
                                                        "${asset.status}"
                                            )
                                        }
                                    ) {

                                        Icon(
                                            Icons.Default.Edit,
                                            contentDescription = null
                                        )

                                        Spacer(modifier = Modifier.width(4.dp))

                                        Text("Edit")
                                    }

                                    TextButton(
                                        onClick = {

                                            FirebaseFirestore.getInstance()
                                                .collection("assets")
                                                .document(asset.id)
                                                .delete()
                                        }
                                    ) {

                                        Icon(
                                            Icons.Default.Delete,
                                            contentDescription = null,
                                            tint = Color.Red
                                        )

                                        Spacer(modifier = Modifier.width(4.dp))

                                        Text(
                                            text = "Delete",
                                            color = Color.Red
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AssetInfoRow(
    label: String,
    value: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = label,
            color = Color.Gray
        )

        Text(
            text = value,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun StatusBadge(status: String) {

    val background = when (status) {

        "Working" -> Color(0xFFE8F5E9)

        "Needs Repair" -> Color(0xFFFFF3E0)

        else -> Color(0xFFFFEBEE)
    }

    val textColor = when (status) {

        "Working" -> Color(0xFF2E7D32)

        "Needs Repair" -> Color(0xFFEF6C00)

        else -> Color(0xFFC62828)
    }

    Box(
        modifier = Modifier
            .background(
                background,
                RoundedCornerShape(14.dp)
            )
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {

        Text(
            text = status,
            color = textColor,
            fontWeight = FontWeight.SemiBold
        )
    }
}