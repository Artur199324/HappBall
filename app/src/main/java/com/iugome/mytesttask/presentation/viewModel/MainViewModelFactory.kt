package com.iugome.mytesttask.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iugome.mytesttask.domain.userCase.GetUnsplashUserCase
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(val getUnsplashUserCase: GetUnsplashUserCase) :ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return MainViewModel(getUnsplashUserCase = getUnsplashUserCase) as T
    }
}