package com.sid1817733.sid1817733finalassignment.assignmentdatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AssignmentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAssignment(assignment: Assignment)

    @Query("SELECT * from assignment_table WHERE moduleName = :moduleName")
    fun readAllData(moduleName:String?): LiveData<List<Assignment>>

    @Delete
    suspend fun deleteAssignment(assignment: Assignment)

    @Query("DELETE from assignment_table WHERE moduleName = :moduleName")
    suspend fun deleteAssignmentFromModule(moduleName: String?)
}