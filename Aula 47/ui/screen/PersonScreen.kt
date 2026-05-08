package com.example.projetosqlitecapgemini.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.projetosqlitecapgemini.viewModel.PersonViewModel

@Composable
fun PersonScreen(viewModel: PersonViewModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        // Vinculação direta com a ViewModel
        OutlinedTextField(
            value = viewModel.name,
            onValueChange = { viewModel.name = it },
            label = { Text("Nome") }
        )
        OutlinedTextField(
            value = viewModel.age,
            onValueChange = { viewModel.age = it },
            label = { Text("Idade") }
        )

        Button(
            onClick = { viewModel.addPerson() },
            enabled = !viewModel.isSubmitting // Desativa se estiver salvando
        ) {
            Text(if (viewModel.isSubmitting) "Salvando..." else "Cadastrar")
        }

        LazyColumn {
            items(viewModel.persons) { person ->
                Text("${person.id} - ${person.name}")
            }
        }
    }
}
