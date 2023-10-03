package com.example.weatherapp.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.ui.App
import com.example.weatherapp.databinding.FragmentSearchBinding
import com.example.weatherapp.domain.CityModel
import com.example.weatherapp.domain.GetPreviousCityNameUseCase
import com.example.weatherapp.domain.SaveCityNameUseCase
import com.example.weatherapp.utils.Constants
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var saveCityNameUseCase: SaveCityNameUseCase

    @Inject
    lateinit var getCityNameUseCase: GetPreviousCityNameUseCase

    @Inject
    lateinit var viewModelFactory: SearchViewModelFactory
    private val viewModel: SearchViewModel by viewModels { viewModelFactory }
    private val cityNameAdapter = NameCityAdapter { nameCity -> onClickNameCity(nameCity) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).applicationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerCityName.adapter = cityNameAdapter
        val editableText = getCityNameUseCase.getPreviousCityName()
        (binding.editNameCity as TextView).text = editableText
        progressBarState()

        binding.buttonSearch.setOnClickListener {
            val name = binding.editNameCity.text.toString()
            viewModel.getNameCity(name)
            saveCityNameUseCase.saveCityName(name)
        }

        viewModel.cityName.onEach {
            cityNameAdapter.setData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun onClickNameCity(nameCity: CityModel) {
        viewModel.getWeatherInCity(nameCity.name)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.weatherInCity.collect {
                    for (weatherInCity in it) {
                        weatherInCity?.let {
                            if (weatherInCity.cityName == nameCity.name) {
                                viewModel.saveWeatherInCity(weatherInCity)
                                val bundle =
                                    bundleOf(Constants.KEY_BUNDLE_DETAIL_WEATHER to nameCity.name)
                                findNavController().navigate(
                                    R.id.action_navigation_search_to_weatherDetailsFragment,
                                    bundle
                                )
                            }

                        }
                    }
                }
            }
        }
    }

    private fun progressBarState(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.isLoading.collect{
                    when(it){
                        false -> binding.progressBar.isVisible = false
                        true -> binding.progressBar.isVisible = true
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}