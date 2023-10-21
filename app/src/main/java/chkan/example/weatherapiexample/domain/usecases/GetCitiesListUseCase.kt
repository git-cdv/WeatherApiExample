package chkan.example.weatherapiexample.domain.usecases

import chkan.example.weatherapiexample.core.model.SyncUseCase
import javax.inject.Inject


class GetCitiesListUseCase @Inject constructor() :
    SyncUseCase<List<String>, Unit> {

    override fun run(params: Unit): List<String> {
        return listOf("Amsterdam", "Barcelona", "Copenhagen","Frankfurt", "Hong Kong", "Jerusalem",
            "Kyiv", "London", "Madrid","New York", "Paris", "Sydney","Toronto", "Vilnius", "Warsaw")
    }
}