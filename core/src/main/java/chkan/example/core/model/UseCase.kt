package chkan.example.core.model

interface UseCase<RESULT, PARAMS> {
    suspend fun run(params: PARAMS): RESULT
}