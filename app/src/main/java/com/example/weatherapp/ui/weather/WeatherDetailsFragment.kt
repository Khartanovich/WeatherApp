package com.example.weatherapp.ui.weather

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.databinding.FragmentWeatherDetailsBinding
import com.example.weatherapp.ui.App
import com.example.weatherapp.utils.Constants
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class WeatherDetailsFragment : Fragment() {

    private var _binding: FragmentWeatherDetailsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: WeatherDetailsViewModelFactory
    private val viewModel: WeatherDetailsViewModel by viewModels { viewModelFactory }
    private val weatherAdapter = WeatherDetailsAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).applicationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerWeatherDetail.adapter = weatherAdapter
        val cityName = arguments?.getString(Constants.KEY_BUNDLE_DETAIL_WEATHER)
        if (cityName != null) {
            binding.textNameCity.text = cityName
            viewModel.getWeatherInCity(cityName)
        }

        viewModel.weatherInCity.onEach {
            weatherAdapter.setData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}