package com.github.iaroslavomelianenko.recyclerviewcontacts.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.github.iaroslavomelianenko.recyclerviewcontacts.R
import com.github.iaroslavomelianenko.recyclerviewcontacts.databinding.CountryItemBinding
import com.github.iaroslavomelianenko.recyclerviewcontacts.model.Country

class CountryHolder(item: View): RecyclerView.ViewHolder(item) {

    val _binding = CountryItemBinding.bind(item)

    fun fillCountryItem(country: Country) = with(_binding){
        tvCountryId.text = country.countryId.toString()
        tvCountryName.text = country.countryName
        ivFlag.setImageResource(country.countryFlag)
        tvCountryCodeNumber.text = country.countryPhoneCode.toString()
    }
}