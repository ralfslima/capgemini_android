package com.example.exercicioudf.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercicioudf.model.Student
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class StudentViewModel : ViewModel() {

    private val _state = mutableStateOf(Student())
    val state: State<Student> = _state

    private val _uiEffect = MutableSharedFlow<String>()
    val uiEffect = _uiEffect.asSharedFlow()

    sealed class StudentAction {
        data class NameChange(val name: String) : StudentAction()
        data class Grade1Change(val grade: Double) : StudentAction()
        data class Grade2Change(val grade: Double) : StudentAction()
        data class FilterChange(val status: String) : StudentAction()
        data class RemoveStudent(val student: Student) : StudentAction()
        data class EditStudent(val student: Student) : StudentAction()
        data object AddOrUpdateStudent : StudentAction()
    }

    private var editingStudentId: java.util.UUID? = null

    fun onAction(action: StudentAction) {
        when (action) {
            is StudentAction.NameChange -> _state.value = _state.value.copy(name = action.name)
            is StudentAction.Grade1Change -> _state.value = _state.value.copy(grade1 = action.grade)
            is StudentAction.Grade2Change -> _state.value = _state.value.copy(grade2 = action.grade)
            is StudentAction.FilterChange -> _state.value = _state.value.copy(filterStatus = action.status)

            is StudentAction.AddOrUpdateStudent -> {
                val current = _state.value
                val avg = (current.grade1 + current.grade2) / 2
                val status = if (avg >= 7.0) "Aprovado" else "Reprovado"

                val updatedList = if (editingStudentId != null) {
                    // Lógica de Alterar
                    current.students.map {
                        if (it.id == editingStudentId) it.copy(name = current.name, grade1 = current.grade1, grade2 = current.grade2, average = avg, status = status)
                        else it
                    }
                } else {
                    // Lógica de Cadastrar
                    current.students + Student(name = current.name, grade1 = current.grade1, grade2 = current.grade2, average = avg, status = status)
                }

                _state.value = current.copy(students = updatedList, name = "", grade1 = 0.0, grade2 = 0.0)
                val msg = if (editingStudentId != null) "Alterado com sucesso!" else "Cadastrado com sucesso!"
                sendEffect(msg)
                editingStudentId = null
            }

            is StudentAction.EditStudent -> {
                editingStudentId = action.student.id
                _state.value = _state.value.copy(
                    name = action.student.name,
                    grade1 = action.student.grade1,
                    grade2 = action.student.grade2
                )
            }

            is StudentAction.RemoveStudent -> {
                _state.value = _state.value.copy(students = _state.value.students - action.student)
                sendEffect("Removido com sucesso!")
            }
        }
    }

    private fun sendEffect(msg: String) {
        viewModelScope.launch { _uiEffect.emit(msg) }
    }

    // Lógica de Filtragem para a View
    fun getFilteredStudents(): List<Student> {
        val current = _state.value
        return when (current.filterStatus) {
            "Aprovado" -> current.students.filter { it.status == "Aprovado" }
            "Reprovado" -> current.students.filter { it.status == "Reprovado" }
            else -> current.students
        }
    }
}