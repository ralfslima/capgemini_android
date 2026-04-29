package com.example.projetosqlitecapgemini.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class Person (

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val age: Int
    //val email: String = "" // <- Inicializar atributo para garantir a atualização da tabela

)

