package com.example.testmeteoexomind

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MeteoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}