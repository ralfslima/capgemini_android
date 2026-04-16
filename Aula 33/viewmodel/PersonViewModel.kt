package com.example.udfprojeto.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.udfprojeto.model.Person
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class PersonViewModel: ViewModel() {

    // State (Responsável pela comunicação entre model e view)
    private val _state = mutableStateOf(Person())
    val state: State<Person> = _state

    // Side Effects
    private val _uiEffect = MutableSharedFlow<String>()
    val uiEffect = _uiEffect.asSharedFlow()

    // Classe selada (ações da camada ViewModel)
    sealed class PersonAction {
        data class  NameChange(val name: String)     : PersonAction()
        data class  AgeChange(val  age:  Int)        : PersonAction()
        data class  CityChange(val city: String)     : PersonAction()
        data class  RemovePerson(val person: Person) : PersonAction()
        data object AddPerson : PersonAction()
    }

    // Função para administrar as ações
    fun onAction(action: PersonAction) {
        when(action) {
            // Atualizar o Name
            is PersonAction.NameChange -> {
                _state.value = _state.value.copy(name = action.name)
            }

            // Atualizar a Idade
            is PersonAction.AgeChange -> {
                _state.value = _state.value.copy(age = action.age)
            }

            // Atualizar a Cidade
            is PersonAction.CityChange -> {
                _state.value = _state.value.copy(city = action.city)
            }

            // Cadastro
            is PersonAction.AddPerson -> {
                // Acesso ao objeto atual (Name, Age e City)
                val current = _state.value

                // Gerar um objeto do tipo Person
                val newPerson = Person(
                    name = current.name,
                    age = current.age,
                    city = current.city
                )

                // Atualizar o modelo
                _state.value = current.copy(
                    persons = current.persons + newPerson,
                    name = "",
                    city = "",
                    age = 0
                )

                // Side Effects
                viewModelScope.launch {
                    _uiEffect.emit("Pessoa cadastrada com sucesso!")
                }
            }

            // Remover pessoa
            is PersonAction.RemovePerson -> {
                _state.value = _state.value.copy(
                    persons = _state.value.persons - action.person
                )

                // Side Effects
                viewModelScope.launch {
                    _uiEffect.emit("Pessoa removida com sucesso!")
                }
            }

        }
    }

}

/*
* Classe selada
* Object     -> Ao trabalhar com o objeto completo
* Data Class -> Função contendo parâmetros
*/








