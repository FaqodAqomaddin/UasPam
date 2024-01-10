package com.example.uaspam

import android.app.Application
import com.example.uaspam.data.AppContainer
import com.example.uaspam.data.MotorContainer

class DealerApp : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        container = MotorContainer()
    }
}