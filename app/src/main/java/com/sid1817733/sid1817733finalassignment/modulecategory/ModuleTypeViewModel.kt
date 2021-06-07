package com.sid1817733.sid1817733finalassignment.modulecategory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sid1817733.sid1817733finalassignment.moduletypedatabase.ModuleType
import com.sid1817733.sid1817733finalassignment.moduletypedatabase.ModuleTypeDatabase
import com.sid1817733.sid1817733finalassignment.moduletypedatabase.ModuleTypeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ModuleTypeViewModel(application: Application): AndroidViewModel(application) {

     val readAllData: LiveData<List<ModuleType>>
    private val repository: ModuleTypeRepository
    private val _moduleType = MutableLiveData("1")
    val moduletype:LiveData<String> = _moduleType

    fun saveModuleType(newModuleType: String)
    {
        _moduleType.value = newModuleType
    }
    init {
        val moduletypeDao = ModuleTypeDatabase.getDatabase(application).moduletypeDao()
        repository = ModuleTypeRepository(moduletypeDao)
        readAllData = repository.readAllData
    }

    fun selectItem(moduletype: ModuleType) {
        moduletype.moduleTypeName = moduletype.toString()
    }

    fun addModuleType(moduletype: ModuleType){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addModuleType(moduletype)
        }
    }

    fun deleteModuleType(moduletype: ModuleType){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteModuleType(moduletype)
        }
    }

}