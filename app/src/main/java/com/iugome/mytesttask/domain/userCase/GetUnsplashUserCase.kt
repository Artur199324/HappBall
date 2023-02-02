package com.iugome.mytesttask.domain.userCase

import com.iugome.mytesttask.domain.models.UnsplashModel
import com.iugome.mytesttask.domain.rrepository.UnsplashRepositoryInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUnsplashUserCase @Inject constructor (val unsplashRepositoryInterface: UnsplashRepositoryInterface) {

   suspend fun execute():ArrayList<UnsplashModel>{
        return unsplashRepositoryInterface.getUnsplash()
    }

    suspend fun executeSave(link:String):ArrayList<UnsplashModel>{
        return unsplashRepositoryInterface.getSearch(link)
    }
}