package chkan.example.weatherapiexample.ui.mappers


import chkan.example.core.model.Mapper
import chkan.example.domain.models.ForecastWeatherItem
import chkan.example.domain.models.WeatherDomainModel
import javax.inject.Inject

class WeatherDomainToUiForecastMapper @Inject constructor():
    Mapper<List<WeatherDomainModel>, Map<String, List<ForecastWeatherItem>>> {
    override fun map(input: List<WeatherDomainModel>): Map<String, List<ForecastWeatherItem>> {
        return input.associate { it.currentItem.nameCity to it.forecastListByDay }
    }
}