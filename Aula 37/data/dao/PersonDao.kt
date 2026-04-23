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

    // CADASTRO
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(person: Person)

    // ALTERAÇÃO
    @Update()
    suspend fun update(person: Person)

    // REMOÇÃO
    @Delete()
    suspend fun delete(person: Person)

    // SELEÇÃO
    @Query("SELECT * FROM persons")
    suspend fun select(): Flow<List<Person>>
}