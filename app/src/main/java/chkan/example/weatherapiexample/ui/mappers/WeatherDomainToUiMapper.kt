package chkan.example.weatherapiexample.ui.mappers

import chkan.example.weatherapiexample.core.model.Mapper
import chkan.example.weatherapiexample.domain.models.WeatherDomainModel
import chkan.example.weatherapiexample.ui.models.WeatherUiModel
import javax.inject.Inject

class WeatherDomainToUiMapper@Inject constructor(): Mapper<List<WeatherDomainModel>, WeatherUiModel> {
    override fun map(input: List<WeatherDomainModel>): WeatherUiModel {
        return WeatherUiModel(
            currentListItem = input.map { it.currentItem }
        )
    }
}