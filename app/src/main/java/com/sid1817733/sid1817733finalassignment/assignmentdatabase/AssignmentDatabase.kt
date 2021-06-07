package com.sid1817733.sid1817733finalassignment.assignmentdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Assignment::class], version = 1, exportSchema = false)
abstract class AssignmentDatabase: RoomDatabase(){

    abstract fun assignmentDao(): AssignmentDao

    companion object{
        @Volatile
        private var INSTANCE: AssignmentDatabase? = null

        fun getDatabase(context: Context): AssignmentDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null)
            {
                return tempInstance
            }
            synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AssignmentDatabase::class.java, "assignment_database").build()
                INSTANCE =instance
                return instance


            }
        }
    }}
