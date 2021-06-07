package com.sid1817733.sid1817733finalassignment.moduletypedatabase

import androidx.lifecycle.LiveData

class ModuleTypeRepository(private  val moduletypeDao: ModuleTypeDao) {

    val readAllData: LiveData<List<ModuleType>> = moduletypeDao.readAllData()

    suspend fun addModuleType(moduletype: ModuleType)
    {
        moduletypeDao.addModuleType(moduletype)
    }



    suspend fun deleteModuleType(moduletype: ModuleType){
        moduletypeDao.deleteModuleType(moduletype)
    }

}