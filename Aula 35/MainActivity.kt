package com.example.exemplolivedata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.exemplolivedata.ui.screen.IncrementScreen
import com.example.exemplolivedata.ui.theme.ExemploLiveDataTheme
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExemploLiveDataTheme {
                IncrementScreen(viewModel = viewModel())
            }
        }
    }
}