package chkan.example.weatherapiexample.ui.mappers

import chkan.example.weatherapiexample.core.model.Mapper
import chkan.example.weatherapiexample.domain.models.ForecastWeatherItem
import chkan.example.weatherapiexample.domain.models.WeatherDomainModel
import javax.inject.Inject

class WeatherDomainToUiForecastMapper @Inject constructor():
    Mapper<List<WeatherDomainModel>, Map<String, List<ForecastWeatherItem>>> {
    override fun map(input: List<WeatherDomainModel>): Map<String, List<ForecastWeatherItem>> {
        return input.associate { it.currentItem.nameCity to it.forecastListByDay }
    }
}