package chkan.example.weatherapiexample.domain.usecases

import chkan.example.weatherapiexample.core.model.UseCase
import chkan.example.weatherapiexample.domain.WeatherRepository
import chkan.example.weatherapiexample.domain.models.WeatherDomainModel
import javax.inject.Inject

class GetWeatherByCityUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) : UseCase<WeatherDomainModel?, GetWeatherByCityUseCase.Params> {

    override suspend fun run(params: Params): WeatherDomainModel? {
        return try{
            return weatherRepository.getWeatherByCity(params.city)
        } catch(e:Exception){
            null
        }
    }

    class Params(val city: String)
}