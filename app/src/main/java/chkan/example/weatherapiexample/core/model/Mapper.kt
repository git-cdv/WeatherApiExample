package chkan.example.weatherapiexample.core.model

interface Mapper <I, O> {
    fun map(input: I): O
}