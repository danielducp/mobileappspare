package com.sid1817733.sid1817733finalassignment.moduletypedatabase

import android.os.Parcelable
import androidx.room.*
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "module_type_table")
data class ModuleType(
    @PrimaryKey(autoGenerate = true)
    var moduletypeId: Int,
    var moduleTypeName: String
): Parcelable