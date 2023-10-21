package chkan.example.weatherapiexample.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDataModel(
    @SerialName("current")
    val current: Current? = null,
    @SerialName("forecast")
    val forecast: Forecast? = null,
    @SerialName("location")
    val location: Location? = null
)

@Serializable
data class Location(
    @SerialName("name")
    val name: String?
)

@Serializable
data class Current(
    @SerialName("temp_c")
    val tempC: Double?,
    @SerialName("condition")
    val conditionWithIcon: Condition?,
)

@Serializable
data class Forecast(
    @SerialName("forecastday")
    val forecastList: List<Forecastday>?
)

@Serializable
data class Forecastday(
    @SerialName("date")
    val date: String?,
    @SerialName("day")
    val day: Day?
)

@Serializable
data class Day(
    @SerialName("condition")
    val conditionWithIcon: Condition?,
    @SerialName("maxtemp_c")
    val maxtempC: Double?,
    @SerialName("mintemp_c")
    val mintempC: Double?
)

@Serializable
data class Condition(
    @SerialName("icon")
    val icon: String?
)