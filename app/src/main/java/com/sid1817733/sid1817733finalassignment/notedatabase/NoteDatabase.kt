package com.sid1817733.sid1817733finalassignment.notedatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sid1817733.sid1817733finalassignment.note.Converters

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)

abstract class NoteDatabase: RoomDatabase(){

    abstract fun noteDao(): NoteDao

    companion object{
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null)
            {
                return tempInstance
            }
            synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext, NoteDatabase::class.java, "note_database").build()
                INSTANCE =instance
                return instance


            }
        }
    }}
