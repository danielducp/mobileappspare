package com.sid1817733.sid1817733finalassignment.bookdatabase

import android.os.Parcelable
import androidx.room.*
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "book_table")

data class Book(
    @PrimaryKey(autoGenerate = true)
    var bookID: Int,
    var bookName: String,
    var moduleName: String

    ): Parcelable