package com.example.weatherapp.ui.weatherrecords

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.database.WeatherEntity
import com.example.weatherapp.databinding.DatabaseListItemBinding

class WeatherDatabaseAdapter :
    ListAdapter<WeatherEntity, WeatherDatabaseAdapter.DetailsViewHolder>(WeatherDatabaseDC()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        return DetailsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) =
        holder.bind(getItem(position))

    class DetailsViewHolder(
        val binding: DatabaseListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: WeatherEntity) {
            binding.databaseViewModel = data
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): DetailsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DatabaseListItemBinding.inflate(layoutInflater, parent, false)
                return DetailsViewHolder(binding)
            }
        }
    }

    private class WeatherDatabaseDC : DiffUtil.ItemCallback<WeatherEntity>() {
        override fun areItemsTheSame(
            oldItem: WeatherEntity,
            newItem: WeatherEntity
        ) = oldItem.time==newItem.time

        override fun areContentsTheSame(
            oldItem: WeatherEntity,
            newItem: WeatherEntity
        ) = oldItem == newItem
    }

}