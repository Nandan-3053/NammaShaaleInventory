package com.example.nammashaaleinventory.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAssetScreen(
    navController: NavController,
    assetId: String,
    name: String,
    category: String,
    serial: String,
    location: String,
    notes: String,
    status: String
) {

    var assetName by remember {
        mutableStateOf(name)
    }

    var assetCategory by remember {
        mutableStateOf(category)
    }

    var serialNumber by remember {
        mutableStateOf(serial)
    }

    var assetLocation by remember {
        mutableStateOf(location)
    }

    var assetNotes by remember {
        mutableStateOf(notes)
    }

    var assetStatus by remember {
        mutableStateOf(status)
    }

    var categoryExpanded by remember {
        mutableStateOf(false)
    }

    var statusExpanded by remember {
        mutableStateOf(false)
    }

    val categories = listOf(
        "Sports",
        "Lab",
        "Electronics",
        "Furniture"
    )

    val statusOptions = listOf(
        "Working",
        "Needs Repair",
        "Broken"
    )

    val db = FirebaseFirestore.getInstance()

    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text("Edit Asset")
                }
            )
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                text = "Update Asset Details",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Modify your asset information.",
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {

                    Text(
                        text = "Asset Name",
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = assetName,
                        onValueChange = {
                            assetName = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Category",
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    ExposedDropdownMenuBox(
                        expanded = categoryExpanded,
                        onExpandedChange = {
                            categoryExpanded = !categoryExpanded
                        }
                    ) {

                        OutlinedTextField(
                            value = assetCategory,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = categoryExpanded
                                )
                            },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp)
                        )

                        ExposedDropdownMenu(
                            expanded = categoryExpanded,
                            onDismissRequest = {
                                categoryExpanded = false
                            }
                        ) {

                            categories.forEach { option ->

                                DropdownMenuItem(
                                    text = {
                                        Text(option)
                                    },
                                    onClick = {

                                        assetCategory = option
                                        categoryExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Serial Number",
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = serialNumber,
                        onValueChange = {
                            serialNumber = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Location",
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = assetLocation,
                        onValueChange = {
                            assetLocation = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Status",
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    ExposedDropdownMenuBox(
                        expanded = statusExpanded,
                        onExpandedChange = {
                            statusExpanded = !statusExpanded
                        }
                    ) {

                        OutlinedTextField(
                            value = assetStatus,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = statusExpanded
                                )
                            },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp)
                        )

                        ExposedDropdownMenu(
                            expanded = statusExpanded,
                            onDismissRequest = {
                                statusExpanded = false
                            }
                        ) {

                            statusOptions.forEach { option ->

                                DropdownMenuItem(
                                    text = {
                                        Text(option)
                                    },
                                    onClick = {

                                        assetStatus = option
                                        statusExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Notes",
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = assetNotes,
                        onValueChange = {
                            assetNotes = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        shape = RoundedCornerShape(16.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {

                            val updatedAsset = hashMapOf(

                                "assetName" to assetName,

                                "category" to assetCategory,

                                "serialNumber" to serialNumber,

                                "location" to assetLocation,

                                "notes" to assetNotes,

                                "status" to assetStatus
                            )

                            db.collection("assets")
                                .document(assetId)
                                .update(updatedAsset as Map<String, Any>)
                                .addOnSuccessListener {

                                    navController.popBackStack()
                                }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(18.dp)
                    ) {

                        Text("Update Asset")
                    }
                }
            }
        }
    }
}