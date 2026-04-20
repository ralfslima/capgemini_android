package com.example.exemplolivedata.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.exemplolivedata.model.IncrementModel
import com.example.exemplolivedata.viewmodel.IncrementViewModel

@Composable
fun IncrementScreen(viewModel: IncrementViewModel) {

    // Vincular com a camada ViewModel
    val objViewModel by viewModel.obj.observeAsState(IncrementModel())

    // Estrutura
    Column(
        modifier = Modifier.fillMaxSize().padding(all = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Exibir o contador
        Text("Contador: ${objViewModel.counter}")

        // Botão
        Button(onClick = {viewModel.incrementCounter()}) {
            Text("Incrementar +1")
        }
    }

}