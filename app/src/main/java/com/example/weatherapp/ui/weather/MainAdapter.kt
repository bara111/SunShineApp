package com.example.weatherapp.ui.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.data.models.WeatherDailyData
import com.example.weatherapp.databinding.DailyweatherListitemIconBinding
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
        val binding: DailyweatherListitemIconBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: WeatherDailyData) {
            Glide.with(itemView.ctx).load(data.weather?.get(0)?.getUrl())
                .into(binding.imgaeviewListitemIcon)
            binding.itemViewModel = data
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): DetailsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DailyweatherListitemIconBinding.inflate(layoutInflater, parent, false)
                return DetailsViewHolder(binding)
            }
        }
    }

    private class WeatherDC : DiffUtil.ItemCallback<WeatherDailyData>() {
        override fun areItemsTheSame(
            oldItem: WeatherDailyData,
            newItem: WeatherDailyData
        ) = oldItem==newItem

        override fun areContentsTheSame(
            oldItem: WeatherDailyData,
            newItem: WeatherDailyData
        ) = oldItem == newItem
    }

}