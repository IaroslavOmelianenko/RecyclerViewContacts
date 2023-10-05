package com.github.iaroslavomelianenko.recyclerviewcontacts.util

import androidx.recyclerview.widget.DiffUtil
import com.github.iaroslavomelianenko.recyclerviewcontacts.model.Country

class CountryDiffUtil(
    private val oldList: ArrayList<Country>,
    private val newList: ArrayList<Country>
    ): DiffUtil.Callback(){

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].countryId == newList[newItemPosition].countryId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].countryId != newList[newItemPosition].countryId ->{
                false
            }
            oldList[oldItemPosition].countryName != newList[newItemPosition].countryName ->{
                false
            }
            oldList[oldItemPosition].countryPhoneCode != newList[newItemPosition].countryPhoneCode ->{
                false
            }
            else -> true
        }
    }
}