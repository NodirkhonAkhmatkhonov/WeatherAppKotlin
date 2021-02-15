package com.example.weather.WeatherRecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.WeatherItemBinding

class WeatherAdapter(private var items: ArrayList<WeatherModel>) :
    RecyclerView.Adapter<WeatherAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val itemBinding: WeatherItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(model: WeatherModel) {
            itemBinding.cityName.text = model.cityName
            itemBinding.weatherType.text = model.weatherType
            itemBinding.temperature.text = model.temperature
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding = WeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    fun dataChanged(item: WeatherModel) {
        items.add(item)
        notifyItemInserted(items.lastIndex)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount() = items.size
}