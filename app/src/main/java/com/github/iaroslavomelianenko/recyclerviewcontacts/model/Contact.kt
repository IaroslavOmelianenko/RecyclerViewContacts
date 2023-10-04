package com.github.iaroslavomelianenko.recyclerviewcontacts.model

data class Contact(
    val contactId: Int,
    var contactName: String,
    var contactSurname: String,
    var contactPhoneNumber: Long,
    var selected: Boolean
    )


