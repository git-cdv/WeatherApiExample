package chkan.example.weatherapiexample.ui.models

import chkan.example.domain.models.CurrentWeatherItem

data class WeatherUiModel (
    val currentListItem: List<chkan.example.domain.models.CurrentWeatherItem>
)