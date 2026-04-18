package com.example.exercicioudf.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exercicioudf.viewmodel.StudentViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun StudentScreen(viewModel: StudentViewModel = viewModel()) {
    val state by viewModel.state
    val filteredList = viewModel.getFilteredStudents()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collectLatest { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
    }

    Column(modifier = Modifier.fillMaxSize().systemBarsPadding().padding(16.dp)) {
        Text("Controle de Notas", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(value = state.name, onValueChange = { viewModel.onAction(StudentViewModel.StudentAction.NameChange(it)) }, label = { Text("Nome do Aluno") }, modifier = Modifier.fillMaxWidth())

        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
            OutlinedTextField(value = state.grade1.toString(), onValueChange = { viewModel.onAction(StudentViewModel.StudentAction.Grade1Change(it.toDoubleOrNull() ?: 0.0)) }, label = { Text("Nota 1") }, modifier = Modifier.weight(1f), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
            Spacer(Modifier.width(8.dp))
            OutlinedTextField(value = state.grade2.toString(), onValueChange = { viewModel.onAction(StudentViewModel.StudentAction.Grade2Change(it.toDoubleOrNull() ?: 0.0)) }, label = { Text("Nota 2") }, modifier = Modifier.weight(1f), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        }

        Button(onClick = { viewModel.onAction(StudentViewModel.StudentAction.AddOrUpdateStudent) }, modifier = Modifier.fillMaxWidth()) {
            Text("Salvar Registro")
        }

        // Seção de Filtros
        Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            listOf("Todos", "Aprovado", "Reprovado").forEach { filter ->
                FilterChip(
                    selected = state.filterStatus == filter,
                    onClick = { viewModel.onAction(StudentViewModel.StudentAction.FilterChange(filter)) },
                    label = { Text(filter) }
                )
            }
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(filteredList) { student ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(student.name, style = MaterialTheme.typography.titleLarge)
                            Text("Média: ${student.average} - ${student.status}", color = if (student.status == "Aprovado") Color.Blue else Color.Red)
                        }
                        IconButton(onClick = { viewModel.onAction(StudentViewModel.StudentAction.EditStudent(student)) }) { Icon(Icons.Default.Edit, "Editar") }
                        IconButton(onClick = { viewModel.onAction(StudentViewModel.StudentAction.RemoveStudent(student)) }) { Icon(Icons.Default.Delete, "Excluir", tint = Color.Red) }
                    }
                }
            }
        }
    }
}
