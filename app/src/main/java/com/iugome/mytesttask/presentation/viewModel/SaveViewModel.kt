package com.iugome.mytesttask.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.iugome.mytesttask.data.room.EntityUnsplash
import com.iugome.mytesttask.domain.userCase.DeleteRoomUserCase
import com.iugome.mytesttask.domain.userCase.GetRoomUserCase
import com.iugome.mytesttask.domain.userCase.InsertRoomUserCase

class SaveViewModel( val getRoomUserCase: GetRoomUserCase):  ViewModel()  {
    suspend fun getDataBase(): List<EntityUnsplash> {
        return getRoomUserCase.execute()
    }

}