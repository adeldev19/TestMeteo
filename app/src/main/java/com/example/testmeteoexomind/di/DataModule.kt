package com.example.testmeteoexomind.di

import com.example.testmeteoexomind.meteo.MeteoRepository
import com.example.testmeteoexomind.meteo.MeteoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsContactRepo(
        meteoRepositoryImpl: MeteoRepositoryImpl
    ): MeteoRepository

}