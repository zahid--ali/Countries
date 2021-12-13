package com.udemy.countries.model

import com.udemy.countries.di.DaggerApiComponent
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class CountriesService {
    @Inject
    lateinit var api : CountriesApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCountries(): Single<ArrayList<Country>> {
        return api.getCountries()
    }

}