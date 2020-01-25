package com.example.weatherapp.ui.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.databinding.DailyWeatherItemBinding
import com.example.weatherapp.extension.ctx
import com.example.weatherapp.data.models.WeatherDailyData

class WeatherRecycleViewAdapter(
    var list: List<WeatherDailyData>?,
    var itemClick: (WeatherDailyData) -> Unit
) : RecyclerView.Adapter<WeatherRecycleViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: DailyWeatherItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.ctx),
            R.layout.daily_weather_item,
            parent,
            false
        )
        return ViewHolder(
            binding
        ).apply {
            itemView.setOnClickListener {
                list?.get(adapterPosition)?.let {
                    itemClick(it)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list?.get(position)?.let { weather ->
            holder.bind(weather)
        }
    }

    class ViewHolder(
        val binding: DailyWeatherItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private var dailyData: WeatherDailyData? = null
        fun bind(data: WeatherDailyData) {
            Glide.with(itemView.ctx).load(data.weather?.get(0)?.getUrl()).into(binding.iconIv)
            binding.itemViewModel = data
            this.dailyData = data
            binding.executePendingBindings()
        }
    }
}