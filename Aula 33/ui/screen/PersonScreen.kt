package com.example.udfprojeto.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.udfprojeto.R
import com.example.udfprojeto.viewmodel.PersonViewModel
import kotlinx.coroutines.flow.collectLatest


@Composable
fun PersonScreen(viewModel: PersonViewModel = viewModel()) {

    // Referenciar o State do ViewModel
    val state by viewModel.state

    // Focus
    val focusManager = LocalFocusManager.current

    // Side Effects
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEffect.collectLatest { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }

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
                value = if (state.age == 0) "" else state.age.toString(),
                onValueChange = { newValue ->
                    // all -> transforma o valor em uma cadeia de caracteres
                    if(newValue.all { it.isDigit() }) {
                        val newAge = newValue.toIntOrNull() ?: 0

                        viewModel.onAction(
                            PersonViewModel.PersonAction.AgeChange(newAge)
                        )
                    }
                },
                label = { Text("Idade") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.width(4.dp))

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
            focusManager.clearFocus()
        }
        ) {
            Text("Cadastrar")
        }

        // Listagem de pessoas (LazyColumn)
        LazyColumn() {
            // Percorrer nosso List<Person>
            items(state.persons, key = { it.id }) { person ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(all = 16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column() {
                            Text("Nome: ${person.name}")
                            Text("Idade: ${person.age}")
                            Text("Cidade: ${person.city}")
                        }

                        IconButton(
                            onClick = {
                                viewModel.onAction(
                                    PersonViewModel.PersonAction.RemovePerson(person)
                                )
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Remover pessoa",
                                tint = Color.Red,
                                modifier = Modifier.size(50.dp)
                            )
                        }
                    }

                }
            }
        }

    }

}
