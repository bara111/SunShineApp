package com.example.weatherapp.ui.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.local.WeatherEntity
import com.example.weatherapp.databinding.WeatheritemLayoutBinding

class WeatherAdapter: ListAdapter<WeatherEntity, WeatherAdapter.DetailsViewHolder>(WeatherDatabaseDC()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        return DetailsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) =
        holder.bind(getItem(position))

    class DetailsViewHolder(
        val binding: WeatheritemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: WeatherEntity) {
            with(binding) {
                databaseViewModel = data
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): DetailsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = WeatheritemLayoutBinding.inflate(layoutInflater, parent, false)
                return DetailsViewHolder(binding)
            }
        }
    }

    private class WeatherDatabaseDC : DiffUtil.ItemCallback<WeatherEntity>() {
        override fun areItemsTheSame(
            oldItem: WeatherEntity,
            newItem: WeatherEntity
        ) = oldItem.time == newItem.time

        override fun areContentsTheSame(
            oldItem: WeatherEntity,
            newItem: WeatherEntity
        ) = ((oldItem.MaxTemp == newItem.MaxTemp) && (oldItem.MinTemp == newItem.MinTemp))
    }

}