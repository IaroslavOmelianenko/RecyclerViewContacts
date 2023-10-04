package com.github.iaroslavomelianenko.recyclerviewcontacts.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.github.iaroslavomelianenko.recyclerviewcontacts.databinding.ContactItemBinding
import com.github.iaroslavomelianenko.recyclerviewcontacts.model.Contact


class ContactHolder(item: View): RecyclerView.ViewHolder(item) {

    val _binding = ContactItemBinding.bind(item)

    fun fillContactItem(contact: Contact) = with(_binding){
        tvContactId.text = contact.contactId.toString()
        tvContactName.text = contact.contactName
        tvContactSurname.text = contact.contactSurname
        tvContactPhoneNumber.text = contact.contactPhoneNumber.toString()
    }
}
