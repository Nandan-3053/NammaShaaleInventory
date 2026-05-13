package com.example.nammashaaleinventory.model

data class Asset(
    val id: Int,
    val name: String,
    val serialNumber: String,
    val category: String,
    val condition: String
)