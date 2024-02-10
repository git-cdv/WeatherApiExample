package chkan.example.weatherapiexample.di

import chkan.example.data.WeatherRepositoryImpl
import chkan.example.domain.WeatherRepository
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