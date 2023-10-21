package chkan.example.weatherapiexample.domain.models

data class WeatherDomainModel (
    val currentItem: CurrentWeatherItem,
    val forecastListByDay: List<ForecastWeatherItem>
)

data class CurrentWeatherItem (
    val nameCity: String,
    val currentTempC: Double,
    val iconUrl: String
)

data class ForecastWeatherItem (
    val date: String,
    val maxTempC: Double,
    val minTempC: Double,
    val iconUrl: String
)

