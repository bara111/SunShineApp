package com.example.weatherapp.ui.weatherrecords

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.database.WeatherEntity
import com.example.weatherapp.databinding.DatabaseItemLayoutBinding
import com.example.weatherapp.extension.ctx

class WeatherDatabaseRecycleViewAdapter(
    var list: List<WeatherEntity>?,
    var itemClick: (WeatherEntity) -> Unit
) :
    RecyclerView.Adapter<WeatherDatabaseRecycleViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: DatabaseItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.ctx),
            R.layout.database_item_layout,
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
        val binding: DatabaseItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private var dailyData: WeatherEntity? = null
        fun bind(data: WeatherEntity) {
            binding.databaseViewModel = data
            dailyData = data
            binding.executePendingBindings()
        }
    }
}