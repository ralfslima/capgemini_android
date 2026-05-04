package com.example.api2.data.model

data class Student(
    val id: Int? = null,
    val name: String,
    val grade1: Double,
    val grade2: Double,
    val mean: Double? = null
)