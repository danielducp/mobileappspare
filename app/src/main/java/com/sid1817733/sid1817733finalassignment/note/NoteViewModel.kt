package com.sid1817733.sid1817733finalassignment.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sid1817733.sid1817733finalassignment.notedatabase.Note
import com.sid1817733.sid1817733finalassignment.notedatabase.NoteDatabase
import com.sid1817733.sid1817733finalassignment.notedatabase.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {


    private val repository: NoteRepository

    init {
        val noteDao = NoteDatabase.getDatabase(application).noteDao()

        repository = NoteRepository(noteDao, moduleName = null)


    }

    fun addNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(note)
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }

    fun deleteNoteFromModule(moduleName: String?){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNoteFromModule(moduleName)
        }
    }


}