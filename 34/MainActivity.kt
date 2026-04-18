package com.example.exercicioudf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.exercicioudf.ui.screens.StudentScreen
import com.example.exercicioudf.ui.theme.ExercicioUDFTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExercicioUDFTheme {
                StudentScreen()
            }
        }
    }
}