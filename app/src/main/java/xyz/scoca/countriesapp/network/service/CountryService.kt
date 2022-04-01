package xyz.scoca.countriesapp.network.service

import retrofit2.Call
import retrofit2.http.GET
import xyz.scoca.countriesapp.model.CountryData

interface CountryService {

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries() : Call<ArrayList<CountryData>>
}
