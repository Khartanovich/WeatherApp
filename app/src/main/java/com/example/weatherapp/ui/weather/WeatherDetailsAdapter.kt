package com.example.weatherapp.ui.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemRecyclerWeatherDetatailBinding
import com.example.weatherapp.domain.WeatherInCityModel

class WeatherDetailsAdapter : RecyclerView.Adapter<WeatherDetailsAdapter.WeatherDetailsVH>() {

    private var listWeather: List<WeatherInCityModel?> = emptyList()
    fun setData(data: List<WeatherInCityModel?>) {
        this.listWeather = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDetailsVH {
        return WeatherDetailsVH(
            ItemRecyclerWeatherDetatailBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = listWeather.size

    override fun onBindViewHolder(holder: WeatherDetailsVH, position: Int) {
        val weather = listWeather.getOrNull(position)
        holder.bind(weather)
    }

    class WeatherDetailsVH(private val binding: ItemRecyclerWeatherDetatailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WeatherInCityModel?) {
            binding.textTime.text = item?.time
            binding.textTemp.text = item?.tempC.toString()
        }
    }
}