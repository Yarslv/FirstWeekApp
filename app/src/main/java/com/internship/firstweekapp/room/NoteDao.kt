package com.internship.firstweekapp.room

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery


@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAll(): List<Note>
    @Insert
    fun insertAll(vararg users: Note)

    @Delete
    fun deleteAll(user: Note)

    @Query("SELECT * FROM note WHERE :id LIKE id")
    fun getConcrete(id: Int): Note



    @RawQuery
    fun getSortedBy(raw: SimpleSQLiteQuery): List<Note>

    fun getWithRaw(order: String, direction: String): List<Note> {
        val statement = "SELECT * FROM note ORDER BY $order $direction"
        return getSortedBy(SimpleSQLiteQuery(statement))
    }
    @Query("DELETE from note")
    fun deleteAll()

    @Update
    fun update(note: Note)

}
