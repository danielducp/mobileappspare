package com.sid1817733.sid1817733finalassignment.moduletypedatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ModuleTypeDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun addModuleType(moduleType: ModuleType)

    @Query("SELECT * from module_type_table ORDER BY moduletypeId ASC")
    fun readAllData(): LiveData<List<ModuleType>>


  @Delete
  suspend fun deleteModuleType(moduleType: ModuleType)


}