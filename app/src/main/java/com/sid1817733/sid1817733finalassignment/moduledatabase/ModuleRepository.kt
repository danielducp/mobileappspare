package com.sid1817733.sid1817733finalassignment.moduledatabase

import androidx.lifecycle.LiveData


class ModuleRepository(private  val moduleDao: ModuleDao, val moduleType:String?) {
      val readAllData: LiveData<List<Module>> = moduleDao.readAllData(moduleType)

    suspend fun addModule(module: Module)
    {
        moduleDao.addModule(module)
    }

    fun getAllModules(moduleType: String?):LiveData<List<Module>> = moduleDao.readAllData(moduleType)

    suspend fun deleteModule(module: Module){
        moduleDao.deleteModule(module)
    }

    suspend fun deleteModuleFromType(moduleType: String?){
        moduleDao.deleteModuleFromType(moduleType)
    }

    suspend fun readAllDataName(moduleType: String?){
        moduleDao.readAllDataName(moduleType)
    }



}

