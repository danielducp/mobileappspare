package com.sid1817733.sid1817733finalassignment.notedatabase

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao, val moduleName:String?) {


    suspend fun addNote(note: Note)
    {
        noteDao.addNote(note)
    }

    fun getAllNotes(moduleName: String?):LiveData<List<Note>> = noteDao.readAllData(moduleName)

    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }

    suspend fun deleteNoteFromModule(moduleName: String?){
        noteDao.deleteNoteFromModule(moduleName)
    }
}