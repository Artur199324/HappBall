package com.iugome.mytesttask.domain.rrepository

import com.iugome.mytesttask.domain.models.UnsplashModel

interface UnsplashRepositoryInterface {

   suspend fun getUnsplash(): ArrayList<UnsplashModel>
   suspend fun getSearch(link: String): ArrayList<UnsplashModel>
}