package chkan.example.weatherapiexample.ui.screens.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import chkan.example.weatherapiexample.R
import chkan.example.weatherapiexample.databinding.ItemRvCityBinding
import chkan.example.weatherapiexample.domain.models.CurrentWeatherItem
import coil.dispose
import coil.load

interface WeatherActionListener {
    fun onWeatherDetails(city: String)
}

class WeatherListAdapter(
    private val actionListener: WeatherActionListener
) : RecyclerView.Adapter<WeatherViewHolder>(), View.OnClickListener {

    var cities: List<CurrentWeatherItem> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onClick(v: View) {
        val city = v.tag as String
        actionListener.onWeatherDetails(city)
    }

    override fun getItemCount(): Int = cities.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRvCityBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val city = cities[position]
        with(holder.binding) {
            holder.itemView.tag = city.nameCity

            tvCityName.text = city.nameCity
            tvTempC.text = holder.itemView.context.getString(R.string.text_tempC,city.currentTempC)
            if (city.iconUrl.isNotBlank()) {
                ivWeatherIcon.isVisible = true
                ivWeatherIcon.load(city.iconUrl)
            } else {
                ivWeatherIcon.isVisible = false
                ivWeatherIcon.dispose()
            }
        }
    }
}

class WeatherViewHolder(
    val binding: ItemRvCityBinding
) : RecyclerView.ViewHolder(binding.root)