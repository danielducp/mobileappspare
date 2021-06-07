package com.sid1817733.sid1817733finalassignment.bookdatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun addBook(book: Book)

  @Query("SELECT * from book_table WHERE moduleName = :moduleName")
  fun readAllData(moduleName:String?): LiveData<List<Book>>



}