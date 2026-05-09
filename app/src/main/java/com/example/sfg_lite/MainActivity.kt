package com.example.sfg_lite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sfg_lite.navigation.AppNavigation
import com.example.sfg_lite.ui.theme.SpectreFrameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpectreFrameTheme {
                AppNavigation()
            }
        }
    }
}
