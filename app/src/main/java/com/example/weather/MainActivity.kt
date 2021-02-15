package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.WeatherRecycler.WeatherAdapter
import com.example.weather.WeatherRecycler.WeatherModel
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.dialogue.CityDialogue
import com.example.weather.network.Failure
import com.example.weather.network.Success
import com.example.weather.util.makeToast
import com.example.weather.viewmodel.WeatherViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private lateinit var items: ArrayList<WeatherModel>
    private lateinit var weatherAdapter: WeatherAdapter

    private lateinit var viewModel: WeatherViewModel

    private val broadcastChannel = BroadcastChannel<String>(Channel.BUFFERED)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        implementRecyclerView()

        binding.fabAdd.setOnClickListener(this)

        viewModel.liveDataWeather.observe(this, {
            if (it is Success) {
                with(it.data) {
                    weatherAdapter.dataChanged(
                        WeatherModel(name, ", ${weather[0].main}", kelvinToCelcius(main.temp))
                    )
                }
            } else if (it is Failure){
                makeToast(it.errorMessage)
            }
        })

        GlobalScope.launch {
            broadcastChannel.asFlow().collect {
                viewModel.getWeatherData(it)
            }
        }
    }

    private fun kelvinToCelcius(kelvinValue: String) = Math.round(kelvinValue.toFloat() - 273.15).toString()

    private fun implementRecyclerView() {
        items = ArrayList()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        weatherAdapter = WeatherAdapter(items)
        binding.recyclerView.adapter = weatherAdapter
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fabAdd -> {
                val dialog = CityDialogue()
                dialog.show(supportFragmentManager, "CityDialogue")

                dialog.setListener(broadcastChannel)
            }
        }
    }

}