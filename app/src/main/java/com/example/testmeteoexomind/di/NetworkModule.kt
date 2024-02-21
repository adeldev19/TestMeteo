package com.example.testmeteoexomind.di

import com.example.testmeteoexomind.network.MeteoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val timeOut = 20L
    private const val retrofitUrl = "https://api.openweathermap.org/data/2.5/"

    @Provides
    fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                readTimeout(timeOut, TimeUnit.SECONDS)
                connectTimeout(timeOut, TimeUnit.SECONDS)
            }
            .build()
    }

    @Provides
    fun createGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun buildRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(retrofitUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun createMeteoApiService(retrofit: Retrofit): MeteoApi {
        return retrofit.create(MeteoApi::class.java)
    }

}