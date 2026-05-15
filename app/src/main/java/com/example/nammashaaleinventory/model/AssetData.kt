package com.example.nammashaaleinventory.model

data class AssetData(

    val id: String = "",

    val assetName: String = "",

    val category: String = "",

    val serialNumber: String = "",

    val location: String = "",

    val notes: String = "",

    val status: String = "Working"
)