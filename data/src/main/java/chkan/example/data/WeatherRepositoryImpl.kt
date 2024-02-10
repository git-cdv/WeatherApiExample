package chkan.example.data

import android.os.Build
import androidx.annotation.RequiresApi
import chkan.example.data.mappers.WeatherDataToDomainMapper
import chkan.example.data.network.WeatherService
import chkan.example.domain.WeatherRepository
import chkan.example.domain.models.WeatherDomainModel
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
    private val dataToDomainMapper: WeatherDataToDomainMapper
) : WeatherRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherByCity(city: String): WeatherDomainModel {
        val weatherData = weatherService.getWeatherByCity(city = city)
        return dataToDomainMapper.map(weatherData)
    }
}