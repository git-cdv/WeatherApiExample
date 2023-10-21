package chkan.example.weatherapiexample.core.model

interface SyncUseCase <RESULT, PARAMS> {
    fun run(params: PARAMS): RESULT
}