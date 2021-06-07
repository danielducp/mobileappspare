package com.sid1817733.sid1817733finalassignment.bookdatabase

import androidx.lifecycle.LiveData



class BookRepository(private val bookDao: BookDao, val moduleName:String?) {


    suspend fun addBook(book: Book)
    {
        bookDao.addBook(book)
    }

    fun getAllBooks(moduleName: String?):LiveData<List<Book>> = bookDao.readAllData(moduleName)

}