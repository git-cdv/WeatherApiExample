package chkan.example.weatherapiexample.core.di

import chkan.example.weatherapiexample.data.WeatherRepositoryImpl
import chkan.example.weatherapiexample.domain.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoriesModule {

    @Binds
    abstract fun provideWeatherRepository(repository: WeatherRepositoryImpl): WeatherRepository

}