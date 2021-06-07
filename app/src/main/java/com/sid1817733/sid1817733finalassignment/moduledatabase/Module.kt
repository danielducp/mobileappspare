package com.sid1817733.sid1817733finalassignment.moduledatabase

import android.os.Parcelable
import androidx.room.*
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "module_table")

data class Module(
    @PrimaryKey(autoGenerate = true)
    var moduleId: Int,
    var moduleName: String,
    var moduleType: String
): Parcelable