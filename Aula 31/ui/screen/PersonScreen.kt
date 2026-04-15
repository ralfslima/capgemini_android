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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.udfprojeto.R


@Composable
fun PersonScreen() {

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
            value = "",
            onValueChange = { },
            label = { Text("Nome") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = { },
                label = { Text("Idade") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = "",
                onValueChange = { },
                label = { Text("Cidade") },
                modifier = Modifier.weight(2f)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {}) {
            Text("Cadastrar")
        }

        // Listagem de pessoas (LazyColumn)


    }

}