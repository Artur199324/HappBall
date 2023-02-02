package com.iugome.mytesttask.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.iugome.mytesttask.domain.models.UnsplashModel
import com.iugome.mytesttask.domain.userCase.GetUnsplashUserCase

class MainViewModel(val getUnsplashUserCase: GetUnsplashUserCase) : ViewModel() {


    suspend fun loadImage(): ArrayList<UnsplashModel> {
        return getUnsplashUserCase.execute()
    }
    suspend fun loadSerch(link: String):ArrayList<UnsplashModel>{
        Log.d("weq","wew")
        return getUnsplashUserCase.executeSave(link)
    }
}