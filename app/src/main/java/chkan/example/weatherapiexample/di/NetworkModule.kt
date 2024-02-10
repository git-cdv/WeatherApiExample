package chkan.example.weatherapiexample.di

import chkan.example.weatherapiexample.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val json by lazy {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }

    @Provides
    fun providesBaseUrl() : String = BuildConfig.API_BASE_URL

    @Provides
    @Singleton
    internal fun provideRetrofit(BASE_URL : String) : Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    internal fun provideWeatherService(retrofit : Retrofit) : chkan.example.data.network.WeatherService = retrofit.create(
        chkan.example.data.network.WeatherService::class.java)
}