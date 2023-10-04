package com.github.iaroslavomelianenko.recyclerviewcontacts.util

import androidx.recyclerview.widget.DiffUtil
import com.github.iaroslavomelianenko.recyclerviewcontacts.model.Contact

class ContactDiffUtil(
    private val oldList: ArrayList<Contact>,
    private val newList: ArrayList<Contact>
): DiffUtil.Callback(){

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].contactId == newList[newItemPosition].contactId
    }

    //Checking all values, comparing old with new
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].contactId != newList[newItemPosition].contactId ->{
                false
            }
            oldList[oldItemPosition].contactName != newList[newItemPosition].contactName ->{
                false
            }
            oldList[oldItemPosition].contactSurname != newList[newItemPosition].contactSurname ->{
                false
            }
            oldList[oldItemPosition].contactPhoneNumber != newList[newItemPosition].contactPhoneNumber ->{
                false
            }
            else -> true
        }
    }
}
