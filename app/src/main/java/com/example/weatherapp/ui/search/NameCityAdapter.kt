package com.example.weatherapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemRecyclerCityNameBinding
import com.example.weatherapp.domain.CityModel

class NameCityAdapter(
    private val onClickNameCity: (CityModel) -> Unit
) : RecyclerView.Adapter<NameCityAdapter.NameCityViewHolder>() {
    private var listCity: List<CityModel> = emptyList()
    fun setData(data: List<CityModel>) {
        this.listCity = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameCityViewHolder {
        return NameCityViewHolder(
            ItemRecyclerCityNameBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = listCity.size

    override fun onBindViewHolder(holder: NameCityViewHolder, position: Int) {
        val item = listCity.getOrNull(position)
        holder.bind(item)
        holder.binding.nameCity.setOnClickListener {
            item?.let { onClickNameCity(it) }
        }
    }

    class NameCityViewHolder(val binding: ItemRecyclerCityNameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CityModel?) {
            binding.nameCity.text = "${item?.name}, ${item?.country}"
        }
    }
}