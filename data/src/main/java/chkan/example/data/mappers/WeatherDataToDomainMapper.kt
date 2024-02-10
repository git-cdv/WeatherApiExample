package chkan.example.data.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import chkan.example.core.extensions.orEmpty
import chkan.example.core.model.Mapper
import chkan.example.data.models.WeatherDataModel
import chkan.example.domain.models.WeatherDomainModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.random.Random

class WeatherDataToDomainMapper @Inject constructor(): Mapper<WeatherDataModel, WeatherDomainModel> {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun map(input: WeatherDataModel): WeatherDomainModel {
        return WeatherDomainModel(
            currentItem = chkan.example.domain.models.CurrentWeatherItem(
                id = Random.nextInt(),
                nameCity = input.location?.name.orEmpty(),
                currentTempC = input.current?.tempC.orEmpty(),
                iconUrl = input.current?.conditionWithIcon?.icon.createIcon()
            ),
            forecastListByDay = input.forecast?.forecastList?.map { item ->
                chkan.example.domain.models.ForecastWeatherItem(
                    id = Random.nextInt(),
                    date = item.date.createDate(),
                    maxTempC = item.day?.maxtempC.orEmpty(),
                    minTempC = item.day?.mintempC.orEmpty(),
                    iconUrl = item.day?.conditionWithIcon?.icon.createIcon()
                )
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

    @RequiresApi(Build.VERSION_CODES.O)
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