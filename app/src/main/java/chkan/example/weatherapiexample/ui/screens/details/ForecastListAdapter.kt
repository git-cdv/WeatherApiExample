package chkan.example.weatherapiexample.ui.screens.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import chkan.example.weatherapiexample.R
import chkan.example.weatherapiexample.databinding.ItemRvForecastBinding
import chkan.example.weatherapiexample.domain.models.ForecastWeatherItem
import coil.load

class ForecastListAdapter : RecyclerView.Adapter<ForecastViewHolder>() {

    var forecasts: List<ForecastWeatherItem> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = forecasts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRvForecastBinding.inflate(inflater, parent, false)
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecast = forecasts[position]
        with(holder.binding) {
            tvDate.text = forecast.date
            tvMaxTempC.text = holder.itemView.context.getString(R.string.text_tempC,forecast.maxTempC)
            tvMinTempC.text = holder.itemView.context.getString(R.string.text_tempC,forecast.minTempC)
            if (forecast.iconUrl.isNotBlank()) {
                ivWeatherIcon.load(forecast.iconUrl)
            } else {
                ivWeatherIcon.load(R.drawable.no_icon_placeholder_24)
            }
        }
    }
}

class ForecastViewHolder(
    val binding: ItemRvForecastBinding
) : RecyclerView.ViewHolder(binding.root)