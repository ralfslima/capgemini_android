package com.example.udfprojeto.model

import java.util.UUID

data class Person(
    val id: UUID = UUID.randomUUID(),
    val name: String = "",
    val age: Int = 0,
    val city: String = "",
    val persons: List<Person> = emptyList()
)
