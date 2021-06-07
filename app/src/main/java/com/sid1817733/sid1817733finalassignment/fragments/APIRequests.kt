package com.sid1817733.sid1817733finalassignment.fragments

import com.sid1817733.sid1817733finalassignment.fragments.api.BooksJson
import retrofit2.Call
import retrofit2.http.GET

const val BASE_URL = "https://openlibrary.org/"
interface ApiRequests {

    @GET("subjects/education.json")
    fun getBooksInfo(): Call<BooksJson>

}