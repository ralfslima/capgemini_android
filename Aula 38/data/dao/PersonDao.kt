package com.example.projetosqlitecapgemini.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.projetosqlitecapgemini.data.entity.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    // ========================
    // CADASTRO
    // ========================

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(person: Person)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(persons: List<Person>)


    // ========================
    // SELEÇÃO
    // ========================

    // Todos os registros
    @Query("SELECT * FROM persons")
    fun getAll(): Flow<List<Person>>

    // Por ID
    @Query("SELECT * FROM persons WHERE id = :id")
    fun getById(id: Int): Person?

    // Por nome exato
    @Query("SELECT * FROM persons WHERE name = :name")
    fun getByName(name: String): Person?

    // Contagem total
    @Query("SELECT COUNT(*) FROM persons")
    fun count(): Int

    // Contagem por idade
    @Query("SELECT COUNT(*) FROM persons WHERE age = :age")
    fun countByAge(age: Int): Int

    // Soma das idades
    @Query("SELECT SUM(age) FROM persons")
    fun sumAge(): Int?

    // Média das idades
    @Query("SELECT AVG(age) FROM persons")
    fun avgAge(): Double?

    // Maior idade
    @Query("SELECT MAX(age) FROM persons")
    fun maxAge(): Int?

    // Menor idade
    @Query("SELECT MIN(age) FROM persons")
    fun minAge(): Int?


    // ========================
    // LIKE
    // ========================

    // Nome contendo o termo (ex: "%jo%")
    @Query("SELECT * FROM persons WHERE name LIKE '%' || :term || '%'")
    fun searchByName(term: String): Flow<List<Person>>


    // ========================
    // WHERE
    // ========================

    // Maior que uma idade
    @Query("SELECT * FROM persons WHERE age > :age")
    fun getOlderThan(age: Int): Flow<List<Person>>

    // Entre duas idades
    @Query("SELECT * FROM persons WHERE age BETWEEN :min AND :max")
    fun getByAgeBetween(min: Int, max: Int): Flow<List<Person>>


    // ========================
    // ORDER BY
    // ========================

    // Ordenado por nome A-Z
    @Query("SELECT * FROM persons ORDER BY name ASC")
    fun getAllOrderByNameAsc(): Flow<List<Person>>

    // Ordenado por nome Z-A
    @Query("SELECT * FROM persons ORDER BY name DESC")
    fun getAllOrderByNameDesc(): Flow<List<Person>>

    // Ordenado por idade crescente
    @Query("SELECT * FROM persons ORDER BY age ASC")
    fun getAllOrderByAgeAsc(): Flow<List<Person>>


    // ========================
    // GROUP BY
    // ========================

    // Contagem de pessoas por idade
    @Query("SELECT age, COUNT(*) as total FROM persons GROUP BY age")
    fun countGroupByAge(): Flow<List<AgeGroup>>


    // ========================
    // ALTERAÇÃO
    // ========================

    @Update
    fun update(person: Person)

    // Atualizar apenas o nome por ID
    @Query("UPDATE persons SET name = :name WHERE id = :id")
    fun updateName(id: Int, name: String)

    // Atualizar apenas a idade por ID
    @Query("UPDATE persons SET age = :age WHERE id = :id")
    fun updateAge(id: Int, age: Int)


    // ========================
    // REMOÇÃO
    // ========================

    @Delete
    fun delete(person: Person)

    // Remover por ID
    @Query("DELETE FROM persons WHERE id = :id")
    fun deleteById(id: Int)

    // Remover por idade
    @Query("DELETE FROM persons WHERE age > :age")
    fun deleteOlderThan(age: Int)

    // Remover todos
    @Query("DELETE FROM persons")
    fun deleteAll()

}

// Classe auxiliar para o GROUP BY
data class AgeGroup(
    val age: Int,
    val total: Int
)