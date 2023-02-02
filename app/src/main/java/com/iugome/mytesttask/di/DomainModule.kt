package com.iugome.mytesttask.di

import com.iugome.mytesttask.data.repository.DatabaseRepository
import com.iugome.mytesttask.domain.rrepository.UnsplashRepositoryInterface
import com.iugome.mytesttask.domain.userCase.DeleteRoomUserCase
import com.iugome.mytesttask.domain.userCase.GetRoomUserCase
import com.iugome.mytesttask.domain.userCase.GetUnsplashUserCase
import com.iugome.mytesttask.domain.userCase.InsertRoomUserCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetUnsplashUserCase(unsplashRepositoryInterface: UnsplashRepositoryInterface): GetUnsplashUserCase{
        return GetUnsplashUserCase(unsplashRepositoryInterface)
    }


    @Provides
    fun provideDeleteRoomUserCase(databaseRepository: DatabaseRepository): DeleteRoomUserCase{
        return DeleteRoomUserCase(databaseRepository)
    }

    @Provides
    fun provideGetRoomUserCase(databaseRepository: DatabaseRepository): GetRoomUserCase{
        return GetRoomUserCase(databaseRepository)
    }

    @Provides
    fun provideInsertRoomUserCase(databaseRepository: DatabaseRepository): InsertRoomUserCase{
        return InsertRoomUserCase(databaseRepository)
    }


}