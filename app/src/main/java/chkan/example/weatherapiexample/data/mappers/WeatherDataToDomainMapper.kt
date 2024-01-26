package chkan.example.weatherapiexample.data.mappers

import chkan.example.weatherapiexample.core.extensions.orEmpty
import chkan.example.weatherapiexample.core.model.Mapper
import chkan.example.weatherapiexample.data.models.WeatherDataModel
import chkan.example.weatherapiexample.domain.models.CurrentWeatherItem
import chkan.example.weatherapiexample.domain.models.ForecastWeatherItem
import chkan.example.weatherapiexample.domain.models.WeatherDomainModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.random.Random

class WeatherDataToDomainMapper @Inject constructor(): Mapper<WeatherDataModel,WeatherDomainModel > {
    override fun map(input: WeatherDataModel): WeatherDomainModel {
        return WeatherDomainModel(
            currentItem = CurrentWeatherItem(
                id = Random.nextInt(),
                nameCity = input.location?.name.orEmpty(),
                currentTempC = input.current?.tempC.orEmpty(),
                iconUrl = input.current?.conditionWithIcon?.icon.createIcon()
            ),
            forecastListByDay = input.forecast?.forecastList?.map { item ->
                ForecastWeatherItem(id = Random.nextInt(),date = item.date.createDate(), maxTempC = item.day?.maxtempC.orEmpty(), minTempC = item.day?.mintempC.orEmpty(), iconUrl = item.day?.conditionWithIcon?.icon.createIcon())
            } ?: emptyList()
        )
    }

    private fun String?.createIcon() : String {
        return if (this.isNullOrEmpty()) {
            ""
        } else {
            "https:$this"
        }
    }

    private fun String?.createDate() : String {
        return if (this.isNullOrEmpty()) {
            "No date"
        } else {
            try {
                val formatter = DateTimeFormatter.ofPattern("MMM dd")
                LocalDate.parse(this).format(formatter)
            } catch (e:Exception) {
                "No date"
            }
        }
    }
}