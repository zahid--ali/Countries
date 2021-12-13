package com.udemy.countries.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udemy.countries.databinding.ItemCountryBinding
import com.udemy.countries.model.Country
import com.udemy.countries.util.getProgressDrawable
import com.udemy.countries.util.loadImage

class CountryListAdapter(val countries: ArrayList<Country>) :
    RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {
    private lateinit var binding: ItemCountryBinding
    fun updateCountries(newCountries: ArrayList<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context))

        return CountryViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    class CountryViewHolder(binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root.rootView) {
        private val countryName = binding.name
        private val countryCapital = binding.capital
        private val imageView = binding.imageView
        private val progressDrawable = getProgressDrawable(binding.root.context)
        fun bind(country: Country) {
            countryName.text = country.countryName
            countryCapital.text = country.capital
            imageView.loadImage(country.flag,progressDrawable)

        }
    }
}