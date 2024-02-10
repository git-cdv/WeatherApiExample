package chkan.example.core.model

interface SyncUseCase <RESULT, PARAMS> {
    fun run(params: PARAMS): RESULT
}