package com.sid1817733.sid1817733finalassignment.assignmentdatabase

import android.database.sqlite.SQLiteDatabase
import androidx.lifecycle.LiveData


class AssignmentRepository(private  val assignmentDao: AssignmentDao, val moduleName:String?) {

    suspend fun addAssignment(assignment: Assignment)
    {
        assignmentDao.addAssignment(assignment)
    }

    fun getAllAssignments(moduleName: String?):LiveData<List<Assignment>> = assignmentDao.readAllData(moduleName)

    suspend fun deleteAssignment(assignment: Assignment){
        assignmentDao.deleteAssignment(assignment)
    }

    suspend fun deleteAssignmentFromModule(moduleName: String?){
        assignmentDao.deleteAssignmentFromModule(moduleName)
    }
}