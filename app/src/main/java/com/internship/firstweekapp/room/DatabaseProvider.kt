package com.internship.firstweekapp.room

import android.content.Context
import androidx.room.Room
import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.arch.mapper.Mapper
import com.internship.firstweekapp.ui.notes_list.note_item.NoteModel
import com.internship.firstweekapp.util.NotesColor

class DatabaseProvider(val context: Context) {
    private val base = Room.databaseBuilder(
        context,
        NotesDataBase::class.java, Constants.DATABASE_NAME
    )
         .fallbackToDestructiveMigration()
        .build()


    fun getAll(): ArrayList<NoteModel> {
        return MMapper().toDomain(base.notesDao().getAll())
    }

    fun deleteAll(){
        base.notesDao().deleteAll()
    }

    fun update(note: Note) {
        base.notesDao().update(note)
    }

    fun getSortedBy(sortField: String, toString: String): ArrayList<NoteModel> {
        return MMapper().toDomain(base.notesDao().getWithRaw(sortField, toString))
    }

    fun deleteAll(note: Note) {
        base.notesDao().deleteAll(note)
    }

    fun add(note: Note) {
        base.notesDao().insertAll(note)
    }

    fun getConcrete(id: Int): Note {
        return base.notesDao().getConcrete(id)
    }

}

class MMapper :
    Mapper<List<Note>, ArrayList<NoteModel>> {
    override fun toDomain(model: List<Note>): ArrayList<NoteModel> {
        val result = arrayListOf<NoteModel>()
        model.forEach {
            result.add(
                NoteModel(
                    title = it.title,
                    content = it.content,
                    color = NotesColor.valueOf(it.color),
                    id = it.id,
                    isEditable = it.isEdit
                )
            )
        }
        return result
    }

}