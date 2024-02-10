package chkan.example.weatherapiexample.ui.mappers

import chkan.example.core.model.Mapper
import chkan.example.domain.models.WeatherDomainModel
import chkan.example.weatherapiexample.ui.models.WeatherUiModel
import javax.inject.Inject

class WeatherDomainToUiMapper@Inject constructor():
    Mapper<List<WeatherDomainModel>, WeatherUiModel> {
    override fun map(input: List<WeatherDomainModel>): WeatherUiModel {
        return WeatherUiModel(
            currentListItem = input.map { it.currentItem }
        )
    }
}