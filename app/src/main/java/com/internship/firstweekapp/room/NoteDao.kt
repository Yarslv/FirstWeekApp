package com.internship.firstweekapp.room

import androidx.room.*

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAll(): List<Note>
    @Insert
    fun insertAll(vararg users: Note)

    @Delete
    fun delete(user: Note)

    @Query("SELECT * FROM note WHERE :id LIKE id")
    fun getConcrete(id: Int): Note
    @Update
    fun update(note: Note)
}