package chkan.example.domain.usecases

import chkan.example.core.model.UseCase
import chkan.example.domain.WeatherRepository
import chkan.example.domain.models.WeatherDomainModel
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