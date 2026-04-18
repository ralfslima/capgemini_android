package com.example.exercicioudf.model

import java.util.UUID

data class Student(
    val id: UUID = UUID.randomUUID(),
    val name: String = "",
    val grade1: Double = 0.0,
    val grade2: Double = 0.0,
    val average: Double = 0.0,
    val status: String = "",
    // Estados de UI
    val students: List<Student> = emptyList(),
    val filterStatus: String = "Todos" // "Todos", "Aprovado", "Reprovado"
)