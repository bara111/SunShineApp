package com.example.weatherapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.data.models.WeatherDailyData
import com.example.weatherapp.databinding.WeatherdailyitemMainBinding
import com.example.weatherapp.extension.ctx

class MainAdapter(
    var itemClick: (WeatherDailyData) -> Unit
) : ListAdapter<WeatherDailyData, MainAdapter.DetailsViewHolder>(WeatherDC()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        return DetailsViewHolder.from(parent).apply {
            itemView.setOnClickListener {
                itemClick(getItem(adapterPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) =
        holder.bind(getItem(position))

    class DetailsViewHolder(
        val binding: WeatherdailyitemMainBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: WeatherDailyData) {
            if (data.weather?.size != 0)
                Glide.with(itemView.ctx).load(data.weather?.get(0)?.getUrl()).into(binding.imgaeviewListitemIcon)
            binding.itemViewModel = data
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): DetailsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = WeatherdailyitemMainBinding.inflate(layoutInflater, parent, false)
                return DetailsViewHolder(binding)
            }
        }
    }

    private class WeatherDC : DiffUtil.ItemCallback<WeatherDailyData>() {
        override fun areItemsTheSame(
            oldItem: WeatherDailyData,
            newItem: WeatherDailyData
        ) = oldItem.getFormatedTime() == newItem.getFormatedTime()

        override fun areContentsTheSame(
            oldItem: WeatherDailyData,
            newItem: WeatherDailyData
        ) =
            ((oldItem.main?.converterTempMax() == newItem.main?.converterTempMax()) && (oldItem.main?.converterTempMin() == newItem.main?.converterTempMin()) && (oldItem.weather?.get(
                0
            )?.description == newItem.weather?.get(0)?.description))
    }
}