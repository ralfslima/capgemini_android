package com.example.api2.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api2.data.model.Student
import com.example.api2.data.repository.StudentRepository
import kotlinx.coroutines.launch

class StudentViewModel : ViewModel() {
    private val repository = StudentRepository()

    // Estado da lista
    var students by mutableStateOf<List<Student>>(emptyList())
        private set

    // Estado de carregamento para desativar o botão enquanto envia
    var isSubmitting by mutableStateOf(false)
        private set

    // Estados dos campos
    var name by mutableStateOf("")
    var grade1 by mutableStateOf("")
    var grade2 by mutableStateOf("")

    init {
        fetchStudents()
    }

    fun fetchStudents() {
        viewModelScope.launch {
            try {
                students = repository.getStudents()
            } catch (e: Exception) {
                // Tratar erro de conexão aqui
            }
        }
    }

    fun addStudent() {
        // Evita cliques duplos e valida se os campos não estão vazios
        if (name.isBlank() || isSubmitting) return

        viewModelScope.launch {
            isSubmitting = true
            try {
                val student = Student(
                    name = name,
                    grade1 = grade1.toDoubleOrNull() ?: 0.0,
                    grade2 = grade2.toDoubleOrNull() ?: 0.0
                    // Não passamos a média aqui, a API que vai gerar
                )

                repository.addStudent(student)

                // Limpa campos e recarrega a lista para pegar a média calculada
                name = ""
                grade1 = ""
                grade2 = ""
                fetchStudents()
            } finally {
                isSubmitting = false
            }
        }
    }

    fun removeStudent(id: Int) {
        viewModelScope.launch {
            repository.removeStudent(id)
            fetchStudents()
        }
    }
}