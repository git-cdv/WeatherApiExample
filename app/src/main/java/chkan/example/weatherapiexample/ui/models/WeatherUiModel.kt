package chkan.example.weatherapiexample.ui.models

import chkan.example.weatherapiexample.domain.models.CurrentWeatherItem

data class WeatherUiModel (
    val currentListItem: List<CurrentWeatherItem>
)