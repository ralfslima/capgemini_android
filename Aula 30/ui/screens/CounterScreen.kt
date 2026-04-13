package com.example.arquiteturaudf.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arquiteturaudf.viewmodel.CounterViewModel

@Composable
fun CounterScreen(viewModel: CounterViewModel = viewModel<CounterViewModel>()) {

    // Acessar o valor do counter (state)
    val state by viewModel.state

    // Estrutura
    Column(modifier = Modifier.padding(20.dp)) {
        // Exibir o valor do counter
        Text(
            text = "Valor do contador: ${state.counter}"
        )

        // Espaçamento
        Spacer(modifier = Modifier.height(20.dp))

        // Linha
        Row {
            Button(
                onClick = {
                    viewModel.incrementDecrement(CounterViewModel.CounterActions.increment)
                }
            ) { Text("Incrementar") }


            Button(
                onClick = {
                    viewModel.incrementDecrement(CounterViewModel.CounterActions.decrement)
                }
            ) { Text("Decrementar") }
        }

    }

}








