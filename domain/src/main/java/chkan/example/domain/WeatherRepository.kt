package chkan.example.domain

import chkan.example.domain.models.WeatherDomainModel

interface WeatherRepository {
    suspend fun getWeatherByCity(city: String) : WeatherDomainModel

}