package com.sid1817733.sid1817733finalassignment.moduledatabase

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ModuleDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun addModule(module: Module)

  @Query("SELECT * from module_table WHERE moduleType = :moduleType")
  fun readAllData(moduleType:String?): LiveData<List<Module>>


  @Query("SELECT * from module_table WHERE moduleType = :moduleType GROUP BY moduleName")
  fun readAllDataName(moduleType:String?): LiveData<List<Module>>


  @Delete
  suspend fun deleteModule(module: Module)

  @Delete
  suspend fun deleteAssignment(module: Module)

  @Query("DELETE from module_table WHERE moduleType = :moduleType")
  suspend fun deleteModuleFromType(moduleType: String?)


}