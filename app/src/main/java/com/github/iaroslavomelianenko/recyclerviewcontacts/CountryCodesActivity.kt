package com.github.iaroslavomelianenko.recyclerviewcontacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.iaroslavomelianenko.recyclerviewcontacts.adapter.CountryAdapter
import com.github.iaroslavomelianenko.recyclerviewcontacts.databinding.ActivityCountryCodesBinding
import com.github.iaroslavomelianenko.recyclerviewcontacts.model.Country

class CountryCodesActivity : AppCompatActivity() {

    lateinit var _binding: ActivityCountryCodesBinding
    private var countryAdapter = CountryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCountryCodesBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        //Filling country list
        val angola = Country(1, "Angola", R.drawable.angola, 244)
        val australia = Country(2, "Australia", R.drawable.australia, 61)
        val austria = Country(3, "Austria", R.drawable.austria, 43)
        val belarus = Country(4, "Belarus", R.drawable.belarus, 375)
        val belgium = Country(5, "Belgium", R.drawable.belgium, 32)
        val bulgaria = Country(6, "Bulgaria", R.drawable.bulgaria, 359)
        val canada = Country(7, "Canada", R.drawable.canada, 1)
        val england = Country(8, "England", R.drawable.england, 44)
        val finland = Country(9, "Finland", R.drawable.finland, 358)
        val germany = Country(10, "Germany", R.drawable.germany, 49)
        val japan = Country(11, "Japan", R.drawable.japan, 81)
        val netherlands = Country(12, "Netherlands", R.drawable.netherlands, 31)
        val russia = Country(13, "Russia", R.drawable.russia, 7)
        val uk = Country(14, "United Kingdom", R.drawable.uk, 44)
        val us = Country(15, "United States", R.drawable.us, 1)
        val countryList = arrayListOf<Country>(angola,australia,austria,belarus,belgium,bulgaria,canada,england,finland,germany,japan,netherlands,russia,uk,us)

        //Launching DiffUtil
        countryAdapter.updateList(countryList)

        _binding.bContacts.setOnClickListener {
            startActivity(Intent(this@CountryCodesActivity, MainActivity::class.java))
        }
        _binding.rcvCountries.layoutManager = LinearLayoutManager(this@CountryCodesActivity)
        _binding.rcvCountries.adapter = countryAdapter
    }
}