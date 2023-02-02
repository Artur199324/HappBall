package com.iugome.mytesttask.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iugome.mytesttask.domain.userCase.GetRoomUserCase
import javax.inject.Inject

class SaveViewModelFactory @Inject constructor( val getRoomUserCase: GetRoomUserCase) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SaveViewModel(getRoomUserCase = getRoomUserCase) as T
    }
}