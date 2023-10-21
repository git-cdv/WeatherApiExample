package chkan.example.weatherapiexample.data.network

import chkan.example.weatherapiexample.data.models.WeatherDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET(GET_WEATHER_BY_CITY)
    suspend fun getWeatherByCity(
        @Query("key") key: String = "a95fa6b11d1948c694b155645231910",
        @Query("q") city: String,
        @Query("days") days: Int = 10,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no"
    ): WeatherDataModel

    companion object {
        const val GET_WEATHER_BY_CITY = "forecast.json?"
    }
}