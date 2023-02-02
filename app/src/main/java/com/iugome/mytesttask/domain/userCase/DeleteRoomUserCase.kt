package com.iugome.mytesttask.domain.userCase

import com.iugome.mytesttask.domain.rrepository.DatabaseRepositoryInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteRoomUserCase @Inject constructor ( val databaseRepositoryInterface: DatabaseRepositoryInterface) {
    suspend fun execute(id:Int){
        databaseRepositoryInterface.deleteEntityUnsplash(id)
    }
}