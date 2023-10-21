package chkan.example.weatherapiexample.domain

import chkan.example.weatherapiexample.domain.models.WeatherDomainModel

interface WeatherRepository {

    suspend fun getWeatherByCity(city: String) : WeatherDomainModel

}