package com.sid1817733.sid1817733finalassignment.fragments.api

data class Availability(
    val __src__: String,
    val available_to_borrow: Boolean,
    val available_to_browse: Boolean,
    val available_to_waitlist: Boolean,
    val collection: String,
    val error_message: String,
    val identifier: String,
    val is_browseable: Boolean,
    val is_lendable: Boolean,
    val is_printdisabled: Boolean,
    val is_readable: Boolean,
    val is_restricted: Boolean,
    val isbn: Any,
    val last_loan_date: Any,
    val last_waitlist_date: Any,
    val num_waitlist: Any,
    val oclc: Any,
    val openlibrary_edition: String,
    val openlibrary_work: String,
    val status: String
)