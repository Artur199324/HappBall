package com.iugome.mytesttask.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.iugome.mytesttask.data.room.EntityUnsplash
import com.iugome.mytesttask.domain.userCase.DeleteRoomUserCase
import com.iugome.mytesttask.domain.userCase.GetRoomUserCase
import com.iugome.mytesttask.domain.userCase.InsertRoomUserCase

class DetailedViewModel(val insertRoomUserCase: InsertRoomUserCase,val getRoomUserCase: GetRoomUserCase,val deleteRoomUserCase: DeleteRoomUserCase):  ViewModel() {

    suspend fun insertt(entityUnsplash: EntityUnsplash){
        insertRoomUserCase.execute(entityUnsplash)
    }

    suspend fun getDataBase(): List<EntityUnsplash> {
        return getRoomUserCase.execute()
    }

    suspend fun delete(id:Int){
        deleteRoomUserCase.execute(id)
    }
}