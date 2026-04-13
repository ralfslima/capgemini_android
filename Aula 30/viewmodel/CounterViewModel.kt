package com.example.arquiteturaudf.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.arquiteturaudf.model.CounterState

// Classe
class CounterViewModel: ViewModel() {

    // Classe selada
    sealed class CounterActions {
        object increment: CounterActions()
        object decrement: CounterActions()
    }

    // Obter o contador do modelo (CounterState)
    private val _state = mutableStateOf(CounterState())

    // Referência para compartilhar com a UI
    val state: State<CounterState> = _state

    // Função para incrementar e decrementar
    fun incrementDecrement(actions: CounterActions) {
        when(actions) {
            // Incremento
            CounterActions.increment ->
            _state.value = _state.value.copy(counter = _state.value.counter + 1)

            // Decremento
            CounterActions.decrement ->
            _state.value = _state.value.copy(counter = _state.value.counter - 1)
        }
    }

}





