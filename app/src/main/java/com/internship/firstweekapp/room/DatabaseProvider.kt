package com.internship.firstweekapp.room

import android.content.Context
import androidx.room.Room
import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.ui.notes_list.note_item.NoteViewModel
import com.internship.firstweekapp.util.NotesColor

class DatabaseProvider(val context: Context) {
    private val base = Room.databaseBuilder(
        context,
        NotesDataBase::class.java, Constants.DATABASE_NAME
    )
//         .fallbackToDestructiveMigration()
        .build()


    fun getAll(): ArrayList<NoteViewModel> {
        val a = base.notesDao().getAll()
        val r = arrayListOf<NoteViewModel>()
        a.forEach {
            r.add(
                NoteViewModel(
                    title = it.title,
                    content = it.content,
                    color = NotesColor.valueOf(it.color),
                    id = it.id,
                    isEditable = it.isEdit
                )
            )
        }

        return r
    }

    fun update(note: Note) {
        base.notesDao().update(note)
    }

    fun delete(note: Note) {
        base.notesDao().delete(note)
    }

    fun add(note: Note) {
        base.notesDao().insertAll(note)
    }

    fun getConcrete(id: Int): Note {
        return base.notesDao().getConcrete(id)
    }

}