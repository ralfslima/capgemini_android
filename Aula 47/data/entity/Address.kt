package com.example.projetosqlitecapgemini.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(
    tableName = "addresses",
    foreignKeys = [
        ForeignKey(
            entity = Person::class,
            parentColumns = ["id"],
            childColumns = ["personId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Address(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val street: String,
    val city: String,
    val personId: Int
)

data class PersonWithAddress(
    // Referenciar a tabela "pai"
    @Embedded val person: Person,

    // Relacionamento
    @Relation(
        parentColumn = "id",
        entityColumn = "personId"
    )
    val address: List<Address>
)








