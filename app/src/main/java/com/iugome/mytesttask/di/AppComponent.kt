package com.iugome.mytesttask.di

import android.content.Context
import com.iugome.mytesttask.presentation.activity.DetailedActivity
import com.iugome.mytesttask.presentation.activity.MainActivity
import com.iugome.mytesttask.presentation.activity.SaveActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DomainModule::class,DataBaseModule::class,RepositoryModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(detailedActivity: DetailedActivity)
    fun inject(saveActivity: SaveActivity)

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}