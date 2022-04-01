package xyz.scoca.countriesapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import xyz.scoca.countriesapp.R
import xyz.scoca.countriesapp.databinding.FragmentCountryDetailBinding
import xyz.scoca.countriesapp.util.extensions.downloadFromUrl
import xyz.scoca.countriesapp.util.extensions.placeHolderProgressBar


class CountryDetailFragment : Fragment() {
    private lateinit var binding: FragmentCountryDetailBinding
    private val args by navArgs<CountryDetailFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountryDetailBinding.inflate(inflater, container, false)
        initDetails()
        return binding.root
    }

    private fun initDetails() {
        binding.ivCountryFlag.downloadFromUrl(
            args.countryData.countryFlagUrl.toString(),
            placeHolderProgressBar(requireContext())
        )
        binding.tvCountryName.text = args.countryData.countryName.toString()
        binding.tvCountryCapital.text = args.countryData.countryCapital.toString()
        binding.tvCountryRegion.text = args.countryData.countryRegion.toString()
        binding.tvCountryCurrency.text = args.countryData.countryCurrency.toString()
        binding.tvCountryaLanguage.text = args.countryData.countryLanguage.toString()
    }

}