package com.udemy.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udemy.countries.di.DaggerApiComponent
import com.udemy.countries.model.CountriesService
import com.udemy.countries.model.Country
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel : ViewModel() {

    @Inject
    lateinit var countriesService : CountriesService
    init {
        DaggerApiComponent.create().inject(this)
    }
    private val disposable = CompositeDisposable()
    val countries = MutableLiveData<ArrayList<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true
        disposable.add(
            countriesService.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ArrayList<Country>>() {
                    override fun onSuccess(value: ArrayList<Country>) {
                        countries.value = value
                        countryLoadError.value = false
                        loading.value = false

                    }

                    override fun onError(e: Throwable) {
                        countryLoadError.value = true
                        loading.value = false
                    }

                })
        )


    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}