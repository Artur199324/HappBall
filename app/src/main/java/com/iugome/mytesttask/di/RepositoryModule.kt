package com.iugome.mytesttask.di

import com.iugome.mytesttask.data.repository.DatabaseRepository
import com.iugome.mytesttask.data.repository.UnsplashRepository
import com.iugome.mytesttask.domain.rrepository.DatabaseRepositoryInterface
import com.iugome.mytesttask.domain.rrepository.UnsplashRepositoryInterface
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindRoomRepository(repositoryImpl: DatabaseRepository): DatabaseRepositoryInterface

    @Binds
    fun bindnsplashRepository(unsplashRepository: UnsplashRepository):UnsplashRepositoryInterface
}