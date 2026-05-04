package com.example.api2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.api2.data.model.Student
import com.example.api2.data.repository.StudentRepository
import com.example.api2.ui.theme.API2Theme

class MainActivity : ComponentActivity() {

    // Referenciar o repositório
    val repository = StudentRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            API2Theme {
                StudentScreen(repository)
            }
        }
    }
}

@Composable
fun StudentScreen(repository: StudentRepository) {

    // Vetor de alunos (Armazenar os alunos vindos da API)
    var students by remember { mutableStateOf<List<Student>>(emptyList()) }

    // Armazenar dados vindos do TextField
    var name   by remember { mutableStateOf("") }
    var grade1 by remember { mutableStateOf("") }
    var grade2 by remember { mutableStateOf("") }

}










