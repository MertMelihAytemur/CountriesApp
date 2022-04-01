package xyz.scoca.countriesapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryData(
    @SerializedName("name")
    val countryName: String? = null,

    @SerializedName("region")
    val countryRegion: String? = null,

    @SerializedName("capital")
    val countryCapital: String? = null,

    @SerializedName("currency")
    val countryCurrency: String? = null,

    @SerializedName("language")
    val countryLanguage: String? = null,

    @SerializedName("flag")
    val countryFlagUrl: String? = null
) : Parcelable
