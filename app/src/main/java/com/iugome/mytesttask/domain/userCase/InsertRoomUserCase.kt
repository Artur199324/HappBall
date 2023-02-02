package com.iugome.mytesttask.domain.userCase

import com.iugome.mytesttask.data.room.EntityUnsplash
import com.iugome.mytesttask.domain.rrepository.DatabaseRepositoryInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InsertRoomUserCase @Inject constructor (val databaseRepositoryInterface: DatabaseRepositoryInterface) {
    suspend fun execute(entityUnsplash: EntityUnsplash){
      databaseRepositoryInterface.insertEntityUnsplash(entityUnsplash)
    }
}