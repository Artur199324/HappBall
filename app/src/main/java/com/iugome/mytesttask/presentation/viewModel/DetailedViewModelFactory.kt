package com.iugome.mytesttask.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iugome.mytesttask.domain.userCase.DeleteRoomUserCase
import com.iugome.mytesttask.domain.userCase.GetRoomUserCase
import com.iugome.mytesttask.domain.userCase.InsertRoomUserCase
import javax.inject.Inject

class DetailedViewModelFactory @Inject constructor(
    val insertRoomUserCase: InsertRoomUserCase,
    val getRoomUserCase: GetRoomUserCase,
    val deleteRoomUserCase: DeleteRoomUserCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailedViewModel(
            insertRoomUserCase = insertRoomUserCase,
            getRoomUserCase = getRoomUserCase,
            deleteRoomUserCase = deleteRoomUserCase
        ) as T
    }
}