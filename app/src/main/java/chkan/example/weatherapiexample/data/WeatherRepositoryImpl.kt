package chkan.example.weatherapiexample.data

import chkan.example.weatherapiexample.data.mappers.WeatherDataToDomainMapper
import chkan.example.weatherapiexample.data.network.WeatherService
import chkan.example.weatherapiexample.domain.WeatherRepository
import chkan.example.weatherapiexample.domain.models.WeatherDomainModel
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
    private val dataToDomainMapper: WeatherDataToDomainMapper
) : WeatherRepository {

    override suspend fun getWeatherByCity(city: String): WeatherDomainModel {
        val weatherData = weatherService.getWeatherByCity(city = city)
        return dataToDomainMapper.map(weatherData)
    }
}