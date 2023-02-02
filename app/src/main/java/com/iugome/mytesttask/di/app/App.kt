package com.iugome.mytesttask.di.app

import android.app.Application
import com.iugome.mytesttask.di.*

class App : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}