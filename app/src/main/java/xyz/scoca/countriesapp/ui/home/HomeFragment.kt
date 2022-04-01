package xyz.scoca.countriesapp.ui.home

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import www.sanju.zoomrecyclerlayout.ZoomRecyclerLayout
import xyz.scoca.countriesapp.databinding.FragmentHomeBinding
import xyz.scoca.countriesapp.model.CountryData
import xyz.scoca.countriesapp.ui.adapter.CountryAdapter
import xyz.scoca.countriesapp.util.OnItemClickListener
import xyz.scoca.countriesapp.util.extensions.snack


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(requireActivity())[HomeViewModel::class.java]
        requireActivity().window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackgroundAnimation()
        setRecyclerViewSnapHelper()

        viewModel.getCountryData()  //fetching data
        observeLiveData() // observe data
    }

    private fun observeLiveData() {
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                binding.rvCountries.visibility = View.VISIBLE
                setRecyclerViewAdapter(countries)
            }
        })

        viewModel.countryOnLoading.observe(viewLifecycleOwner, Observer { onLoading ->
            onLoading?.let { status ->
                if (status) {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.tvErrorMessage.visibility = View.GONE
                    binding.rvCountries.visibility = View.GONE
                } else{
                    binding.progressBar.visibility = View.GONE
                    binding.rvCountries.visibility = View.VISIBLE
                }
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer { error ->
            error?.let { status ->
                if (status) {
                    binding.tvErrorMessage.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    binding.rvCountries.visibility = View.GONE
                    snack(requireView(),"Connection Failed.",60000) { viewModel.getCountryData() }
                } else {
                    binding.tvErrorMessage.visibility = View.GONE
                }
            }
        })
    }

    private fun setBackgroundAnimation() {
        val animDrawable = binding.rootLayout.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(5000)
        animDrawable.start()
    }

    private fun setRecyclerViewAdapter(countryList: ArrayList<CountryData>) {
        val linearLayoutManager = ZoomRecyclerLayout(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        binding.rvCountries.layoutManager = linearLayoutManager
        binding.rvCountries.adapter =
            CountryAdapter(requireContext(), countryList, object : OnItemClickListener {
                override fun onClick(position: Int) {
                    val action =
                        HomeFragmentDirections.actionHomeFragmentToCountryDetailFragment(countryList[position])
                    findNavController().navigate(action)
                }
            })
    }

    private fun setRecyclerViewSnapHelper() {
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvCountries)
        binding.rvCountries.isNestedScrollingEnabled = false
    }
}