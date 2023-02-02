package com.iugome.mytesttask.di

import android.content.Context
import androidx.room.Room
import com.iugome.mytesttask.data.room.DaoUnsplash
import com.iugome.mytesttask.data.room.Database
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule {

    @Provides

    fun provideDataBase(context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java, "my_task"
        ).build()
    }
    @Provides

    fun provideEntityUnsplash(database: Database): DaoUnsplash{
        return database.getDataBase()
    }

}