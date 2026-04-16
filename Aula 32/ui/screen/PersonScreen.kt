package com.example.udfprojeto.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.udfprojeto.R
import com.example.udfprojeto.viewmodel.PersonViewModel


@Composable
fun PersonScreen(viewModel: PersonViewModel = viewModel()) {

    // Referenciar o State do ViewModel
    val state by viewModel.state

    // Estrutura principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.icone_pessoas),
            contentDescription = null
        )

        Text(
            text = "Projeto - UDF",
            modifier = Modifier.padding(top = 20.dp),
            style = MaterialTheme.typography.titleLarge
        )

        OutlinedTextField(
            value = state.name,
            onValueChange = {
                viewModel.onAction(
                    PersonViewModel.PersonAction.NameChange(it)
                )
            },
            label = { Text("Nome") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = state.age.toString(),
                onValueChange = {
                    viewModel.onAction(
                        PersonViewModel.PersonAction.AgeChange(it.toInt())
                    )
                },
                label = { Text("Idade") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = state.city,
                onValueChange = {
                    viewModel.onAction(
                        PersonViewModel.PersonAction.CityChange(it)
                    )
                },
                label = { Text("Cidade") },
                modifier = Modifier.weight(2f)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            viewModel.onAction(PersonViewModel.PersonAction.AddPerson)
        }
        ) {
            Text("Cadastrar")
        }

        // Listagem de pessoas (LazyColumn)
        LazyColumn() {
            // Percorrer nosso List<Person>
            items(state.persons, key = { it.id }) { person ->
                Card() { }
            }
        }

    }

}
