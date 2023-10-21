package chkan.example.weatherapiexample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chkan.example.weatherapiexample.core.model.ErrorResult
import chkan.example.weatherapiexample.core.model.PendingResult
import chkan.example.weatherapiexample.core.model.SuccessResult
import chkan.example.weatherapiexample.core.views.LiveResult
import chkan.example.weatherapiexample.core.views.MutableLiveResult
import chkan.example.weatherapiexample.domain.models.ForecastWeatherItem
import chkan.example.weatherapiexample.domain.models.WeatherDomainModel
import chkan.example.weatherapiexample.domain.usecases.GetCitiesListUseCase
import chkan.example.weatherapiexample.domain.usecases.GetWeatherByCityUseCase
import chkan.example.weatherapiexample.ui.mappers.WeatherDomainToUiForecastMapper
import chkan.example.weatherapiexample.ui.mappers.WeatherDomainToUiMapper
import chkan.example.weatherapiexample.ui.models.WeatherUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor(
    private val getCitiesList : GetCitiesListUseCase,
    private val getWeatherByCity : GetWeatherByCityUseCase,
    private val domainToUiMapper: WeatherDomainToUiMapper,
    private val domainToUiForecastMapper: WeatherDomainToUiForecastMapper,
) : ViewModel() {

    private val _citiesWithWeatherResource = MutableLiveResult<WeatherUiModel>(PendingResult())
    val citiesWithWeatherResource: LiveResult<WeatherUiModel> = _citiesWithWeatherResource

    var citiesWithForecast : Map<String, List<ForecastWeatherItem>> = mutableMapOf()

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch(Dispatchers.IO) {
            val deferredResults = mutableListOf<Deferred<WeatherDomainModel?>>()
            val listCities = getCitiesList.run(Unit)
            listCities.onEach { city ->
                deferredResults.add(
                    async{ getWeatherByCity.run(GetWeatherByCityUseCase.Params(city)) }
                )
            }
            deferredResults.awaitAll().filterNotNull().also { list ->
                val result = if (list.isEmpty()) ErrorResult(Exception("No data from API"))
                else SuccessResult(domainToUiMapper.map(list))
                _citiesWithWeatherResource.postValue(result)
                saveForecastData(list)
            }
        }
    }

    private fun saveForecastData(list: List<WeatherDomainModel>) {
        if (list.isNotEmpty()) {
            citiesWithForecast = domainToUiForecastMapper.map(list)
        }
    }

    fun getForecastDataByCity(city: String) = citiesWithForecast[city]

    fun tryAgain() {
        _citiesWithWeatherResource.postValue(PendingResult())
        load()
    }

}