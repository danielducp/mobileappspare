package com.sid1817733.sid1817733finalassignment.assignments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sid1817733.sid1817733finalassignment.assignmentdatabase.Assignment
import com.sid1817733.sid1817733finalassignment.assignmentdatabase.AssignmentDatabase
import com.sid1817733.sid1817733finalassignment.assignmentdatabase.AssignmentRepository
import com.sid1817733.sid1817733finalassignment.moduledatabase.Module
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AssignmentViewModel(application: Application): AndroidViewModel(application) {


    private val repository: AssignmentRepository

    init {
        val assignmentDao = AssignmentDatabase.getDatabase(application).assignmentDao()

        repository = AssignmentRepository(assignmentDao, moduleName = null)


    }

    fun addAssignment(assignment: Assignment){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAssignment(assignment)
        }
    }
    fun deleteAssignment(assignment: Assignment){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAssignment(assignment)
        }
    }

    fun deleteAssignmentFromModule(moduleName: String?){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAssignmentFromModule(moduleName)
        }
    }



}