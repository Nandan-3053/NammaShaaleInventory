package com.example.nammashaaleinventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.nammashaaleinventory.navigation.AppNavigation
import com.example.nammashaaleinventory.ui.theme.NammaShaaleInventoryTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NammaShaaleInventoryTheme {
                AppNavigation()
            }
        }
    }
}