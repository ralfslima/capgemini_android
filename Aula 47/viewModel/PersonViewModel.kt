package com.example.projetosqlitecapgemini.viewModel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.projetosqlitecapgemini.data.database.AppDatabase
import com.example.projetosqlitecapgemini.data.entity.Person // Seu Model/Entity
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch

// AndroidViewModel fornece o 'Application Context', permitindo acessar o banco de dados (Room)
class PersonViewModel(application: Application) : AndroidViewModel(application) {
    // Acessando o DAO diretamente como você sugeriu para remover repository/factory
    private val personDao = AppDatabase.getDatabase(application).personDao()

    // --- ESTADOS DA UI ---
    var persons by mutableStateOf<List<Person>>(emptyList())
        private set

    var name by mutableStateOf("")
    var age by mutableStateOf("")
    var isSubmitting by mutableStateOf(false)
        private set

    init {
        observePersons()
    }

    private fun observePersons() {
        viewModelScope.launch {
            personDao.select().collect { listaAtualizada ->
                persons = listaAtualizada
            }
        }
    }

    fun addPerson() {
        if (name.isBlank() || isSubmitting) return

        viewModelScope.launch(Dispatchers.IO) {
            isSubmitting = true
            try {
                val newPerson = Person(
                    name = name,
                    age = age.toIntOrNull() ?: 0
                )
                personDao.insert(newPerson)

                // Limpa os campos após o insert bem sucedido
                name = ""
                age = ""
            } finally {
                isSubmitting = false
            }
        }
    }
}