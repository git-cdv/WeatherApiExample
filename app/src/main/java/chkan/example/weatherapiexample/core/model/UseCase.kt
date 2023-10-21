package chkan.example.weatherapiexample.core.model

interface UseCase<RESULT, PARAMS> {
    suspend fun run(params: PARAMS): RESULT
}