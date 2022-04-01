package xyz.scoca.countriesapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.scoca.countriesapp.model.CountryData
import xyz.scoca.countriesapp.network.NetworkHelper

class HomeViewModel : ViewModel() {
    val countries = MutableLiveData<ArrayList<CountryData>>()
    val countryError = MutableLiveData<Boolean>()
    val countryOnLoading = MutableLiveData<Boolean>()

    fun getCountryData(){
        countryOnLoading.value = true
        NetworkHelper().countryService?.getCountries()
            ?.enqueue(object : Callback<ArrayList<CountryData>>{
                override fun onResponse(
                    call: Call<ArrayList<CountryData>>,
                    response: Response<ArrayList<CountryData>>
                ) {
                    countryError.value = false
                    countryOnLoading.value = false
                    countries.value = response.body()
                }

                override fun onFailure(call: Call<ArrayList<CountryData>>, t: Throwable) {
                   countryOnLoading.value = false
                    countryError.value = true
                }
            })
    }
}