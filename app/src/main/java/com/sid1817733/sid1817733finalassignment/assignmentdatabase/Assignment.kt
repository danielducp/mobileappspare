package com.sid1817733.sid1817733finalassignment.assignmentdatabase

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "assignment_table")
data class Assignment(
    @PrimaryKey(autoGenerate = true)
    var assignmentId: Int,
    var assignmentName: String,

    var assignmentInformation: String,
    var moduleName: String
): Parcelable