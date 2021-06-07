package com.sid1817733.sid1817733finalassignment.notedatabase


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "note_table")@Parcelize


data class Note(@ColumnInfo(name = "noteName") var noteName:String,@ColumnInfo(name = "noteInformation") var noteInformation:String, @ColumnInfo(name = "moduleName") var moduleName:String, @ColumnInfo(name = "image",typeAffinity = ColumnInfo.BLOB) var image:ByteArray):Parcelable {
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="noteId")
    var noteId:Int=0 ;
}


