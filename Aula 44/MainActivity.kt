package com.example.api2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.api2.data.model.Student
import com.example.api2.data.repository.StudentRepository
import com.example.api2.ui.theme.API2Theme
import kotlinx.coroutines.launch

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

    // Scope - Serve para realizar requisição/ações sem travar a thread principal
    val scope = rememberCoroutineScope()

    // Carregar a lista de alunos que está na API
    LaunchedEffect(Unit) {
        students = repository.getStudents()
    }

    // UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(20.dp)
    ) {
        // ********** FORMULÁRIO DE CADASTRO
        TextField(
            value = name,
            onValueChange = { name = it},
            label = { Text("Nome") },
            modifier = Modifier.padding(bottom = 5.dp)
        )
        TextField(
            value = grade1,
            onValueChange = { grade1 = it},
            label = { Text("Nota 1") },
            modifier = Modifier.padding(bottom = 5.dp)
        )
        TextField(
            value = grade2,
            onValueChange = { grade2 = it},
            label = { Text("Nota 2") },
            modifier = Modifier.padding(bottom = 5.dp)
        )
        Button(
            onClick = {
                scope.launch {
                    // Objeto do tipo Student
                    val student = Student(
                        name = name,
                        grade1 = grade1.toDouble(),
                        grade2 = grade2.toDouble()
                    )

                    // Efetuar o cadastro via API
                    repository.addStudent(student)

                    // Atualizar a listagem de alunos
                    students = repository.getStudents()

                    // Limpar os TextFields
                    name = ""
                    grade1 = ""
                    grade2 = ""
                }
            }
        ) { Text("Cadastrar") }





        // ********** LISTAGEM DE ALUNOS
        LazyColumn {
            items(students) { student ->

                // Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                ) {
                    // Linha
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Coluna
                        Column {
                            Text("Aluno(a): ${student.name}")
                            Text("Média: ${student.mean}")
                        }

                        // Ícone de lixeira
                        IconButton(
                            onClick = {
                                scope.launch {
                                    student.id?.let {
                                        repository.removeStudent(it)
                                        students = repository.getStudents()
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Remover"
                            )
                        }


                    }
                }

            }
        }

    }

}










