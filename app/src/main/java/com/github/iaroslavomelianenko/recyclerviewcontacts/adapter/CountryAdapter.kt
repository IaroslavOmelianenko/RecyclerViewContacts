package com.github.iaroslavomelianenko.recyclerviewcontacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.iaroslavomelianenko.recyclerviewcontacts.R
import com.github.iaroslavomelianenko.recyclerviewcontacts.holder.CountryHolder
import com.github.iaroslavomelianenko.recyclerviewcontacts.model.Country
import com.github.iaroslavomelianenko.recyclerviewcontacts.util.CountryDiffUtil

class CountryAdapter: RecyclerView.Adapter<CountryHolder>() {

    private var countryList = ArrayList<Country>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)
        return CountryHolder(view)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        val item = countryList[position]
        holder.fillCountryItem(item)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    //DiffUtil
    fun updateList(newCountryList: ArrayList<Country>) {
        val diffUtil = CountryDiffUtil(countryList, newCountryList)
        val diffUtilResults = DiffUtil.calculateDiff(diffUtil)
        countryList.clear()
        countryList.addAll(newCountryList)
        diffUtilResults.dispatchUpdatesTo(this)
    }

}