package com.sid1817733.sid1817733finalassignment.module

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sid1817733.sid1817733finalassignment.moduledatabase.Module
import com.sid1817733.sid1817733finalassignment.moduledatabase.ModuleDatabase
import com.sid1817733.sid1817733finalassignment.moduledatabase.ModuleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ModuleViewModel(application: Application): AndroidViewModel(application) {

    private val repository: ModuleRepository
    private val _module = MutableLiveData("Test")
    val module:LiveData<String> = _module

    val readAllData: LiveData<List<Module>>

    fun saveModule(newModule: String)
    {
        _module.value = newModule
    }


    init {
        val moduleDao = ModuleDatabase.getDatabase(application).moduleDao()

        repository = ModuleRepository(moduleDao, moduleType = null)
        readAllData = repository.readAllData

    }

    fun addModule(module: Module){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addModule(module)
        }
    }

    fun deleteModule(module: Module){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteModule(module)
        }
    }

    fun readAllDataName(moduleType:String?){
        viewModelScope.launch(Dispatchers.IO) {
            repository.readAllDataName(moduleType)
        }
    }

    fun deleteModuleFromType(moduleType: String?){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteModuleFromType(moduleType)
        }
    }


}