package com.sid1817733.sid1817733finalassignment.moduledatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sid1817733.sid1817733finalassignment.moduletypedatabase.ModuleType

@Database(entities = [Module::class, ModuleType::class],  version = 1, exportSchema = false)
abstract class ModuleDatabase: RoomDatabase(){

        abstract fun moduleDao(): ModuleDao

    companion object{
        @Volatile
        private var INSTANCE: ModuleDatabase? = null

        fun getDatabase(context: Context):ModuleDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null)
            {
                return tempInstance
            }
            synchronized(this)
            {
                val instance = Room.databaseBuilder(
              context.applicationContext, ModuleDatabase::class.java, "module_database").build()
                INSTANCE =instance
                return instance


            }
        }
    }
}