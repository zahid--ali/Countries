package com.udemy.countries.di

import com.udemy.countries.model.CountriesService
import com.udemy.countries.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: CountriesService)

    fun inject(viewModel: ListViewModel)
}