package com.example.nammashaaleinventory.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nammashaaleinventory.model.AssetData
import com.example.nammashaaleinventory.ui.components.BottomNavBar
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {

    var assetList by remember {
        mutableStateOf(listOf<AssetData>())
    }

    val db = FirebaseFirestore.getInstance()

    LaunchedEffect(Unit) {

        db.collection("assets")
            .addSnapshotListener { value, error ->

                if (value != null) {

                    assetList = value.documents.mapNotNull {

                        it.toObject(AssetData::class.java)
                    }
                }
            }
    }

    val totalAssets = assetList.size

    val workingAssets = assetList.count {
        it.status == "Working"
    }

    val repairAssets = assetList.count {
        it.status == "Needs Repair"
    }

    val brokenAssets = assetList.count {
        it.status == "Broken"
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
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .padding(16.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF1565C0),
                                Color(0xFF42A5F5)
                            )
                        ),
                        shape = RoundedCornerShape(28.dp)
                    )
                    .padding(24.dp)
            ) {

                Column {

                    Text(
                        text = "Namma Shaale",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Smart School Asset Management",
                        color = Color.White.copy(alpha = 0.9f)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = totalAssets.toString(),
                        color = Color.White,
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Total Assets",
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Overview",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                DashboardStatCard(
                    modifier = Modifier.weight(1f),
                    title = "Working",
                    count = workingAssets.toString(),
                    color = Color(0xFF4CAF50),
                    icon = {
                        Icon(
                            Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                )

                DashboardStatCard(
                    modifier = Modifier.weight(1f),
                    title = "Repair",
                    count = repairAssets.toString(),
                    color = Color(0xFFFF9800),
                    icon = {
                        Icon(
                            Icons.Default.Build,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            DashboardStatCard(
                modifier = Modifier.fillMaxWidth(),
                title = "Broken Assets",
                count = brokenAssets.toString(),
                color = Color(0xFFF44336),
                icon = {
                    Icon(
                        Icons.Default.Warning,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            )

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Recent Assets",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (assetList.isEmpty()) {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "📦",
                            style = MaterialTheme.typography.displaySmall
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "No Assets Added",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Start by adding your first asset.",
                            color = Color.Gray
                        )
                    }
                }

            } else {

                assetList.takeLast(3).reversed().forEach { asset ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 14.dp),
                        shape = RoundedCornerShape(22.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 3.dp
                        )
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Column {

                                Text(
                                    text = asset.assetName,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = asset.location,
                                    color = Color.Gray
                                )
                            }

                            StatusBadge(asset.status)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DashboardStatCard(
    modifier: Modifier,
    title: String,
    count: String,
    color: Color,
    icon: @Composable () -> Unit
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = color
        )
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            icon()

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = count,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = title,
                color = Color.White.copy(alpha = 0.9f)
            )
        }
    }
}