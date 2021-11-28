package com.example.agrolibre.model

import androidx.room.*

@Dao
interface PersonDao {
    @Query("SELECT * FROM Person")
    fun getAll(): List<Person>

    @Query("SELECT * FROM Person WHERE id = :id")
    fun getById(id: Int): Person

    @Update
    fun update(person: Person)

    @Insert
    fun insert(person: Person)

    @Delete
    fun delete(person: Person)
}