package chkan.example.core.model

interface Mapper <I, O> {
    fun map(input: I): O
}