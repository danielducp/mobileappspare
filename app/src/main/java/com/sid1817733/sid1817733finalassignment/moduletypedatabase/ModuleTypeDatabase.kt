package com.sid1817733.sid1817733finalassignment.moduletypedatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ModuleType::class], version = 1, exportSchema = false)
abstract class ModuleTypeDatabase: RoomDatabase(){

        abstract fun moduletypeDao(): ModuleTypeDao

    companion object{
        @Volatile
        private var INSTANCE: ModuleTypeDatabase? = null

        fun getDatabase(context: Context):ModuleTypeDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null)
            {
                return tempInstance
            }
            synchronized(this)
            {
                val instance = Room.databaseBuilder(
              context.applicationContext, ModuleTypeDatabase::class.java, "module_type_database").build()
                INSTANCE =instance
                return instance


            }
        }
    }
}