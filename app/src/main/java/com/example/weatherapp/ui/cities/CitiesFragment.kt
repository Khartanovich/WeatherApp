package com.example.weatherapp.ui.cities

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentCitiesBinding
import com.example.weatherapp.domain.CityModel
import com.example.weatherapp.ui.App
import com.example.weatherapp.ui.search.NameCityAdapter
import com.example.weatherapp.utils.Constants
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CitiesFragment : Fragment() {

    private var _binding: FragmentCitiesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: CitiesViewModelFactory
    private val viewModel: CitiesViewModel by viewModels { viewModelFactory }
    private val cityAdapter = NameCityAdapter { nameCity -> onClickNameCity(nameCity) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).applicationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerCities.adapter = cityAdapter
        viewModel.cities.onEach {
            cityAdapter.setData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClickNameCity(nameCity: CityModel) {
        val bundle = bundleOf(Constants.KEY_BUNDLE_DETAIL_WEATHER to nameCity.name)
        findNavController().navigate(
            R.id.action_navigation_cities_to_weatherDetailsFragment,
            bundle
        )
    }
}