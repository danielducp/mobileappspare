package com.sid1817733.sid1817733finalassignment.fragments.api


data class BooksJson(
    val ebook_count: Int,
    val key: String,
    val name: String,
    val subject_type: String,
    val work_count: Int,
    val works: List<Work>
)