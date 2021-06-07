package com.sid1817733.sid1817733finalassignment.notedatabase

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)

    @Query("SELECT * from note_table  WHERE moduleName = :moduleName")
    fun readAllData(moduleName:String?): LiveData<List<Note>>


    @Delete
    suspend fun deleteNote(note: Note)

    @Query("DELETE from note_table WHERE moduleName = :moduleName")
    suspend fun deleteNoteFromModule(moduleName: String?)

}