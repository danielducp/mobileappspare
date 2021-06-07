package com.sid1817733.sid1817733finalassignment.book

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sid1817733.sid1817733finalassignment.bookdatabase.Book
import com.sid1817733.sid1817733finalassignment.bookdatabase.BookDatabase
import com.sid1817733.sid1817733finalassignment.bookdatabase.BookRepository


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(application: Application): AndroidViewModel(application) {

    private val _book = MutableLiveData("Test")
    val book:LiveData<String> = _book
    private val repository: BookRepository

    init {
        val bookDao = BookDatabase.getDatabase(application).bookDao()

        repository = BookRepository(bookDao, moduleName = null)


    }
    fun saveBook(newBook: String)
    {
        _book.value = newBook
    }

    fun addBook(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBook(book)
        }
    }

}