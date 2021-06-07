package com.sid1817733.sid1817733finalassignment.fragments.api

data class Work(
    val authors: List<Author>,
    val availability: Availability,
    val checked_out: Boolean,
    val cover_edition_key: String,
    val cover_id: Int,
    val edition_count: Int,
    val first_publish_year: Any,
    val has_fulltext: Boolean,
    val ia: String,
    val ia_collection: List<String>,
    val key: String,
    val lending_edition: String,
    val lending_identifier: String,
    val lendinglibrary: Boolean,
    val printdisabled: Boolean,
    val public_scan: Boolean,
    val subject: List<String>,
    val title: String
)