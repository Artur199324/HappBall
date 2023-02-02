package com.iugome.mytesttask.data.repository

import com.iugome.mytesttask.data.room.DaoUnsplash
import com.iugome.mytesttask.data.room.EntityUnsplash
import com.iugome.mytesttask.domain.rrepository.DatabaseRepositoryInterface
import javax.inject.Inject

class DatabaseRepository @Inject constructor(var daoUnsplash: DaoUnsplash )  :
    DatabaseRepositoryInterface {

    override suspend fun getEntityUnsplash(): List<EntityUnsplash> {
        return daoUnsplash.getEntityUnsplash()
    }

    override suspend fun insertEntityUnsplash(entityUnsplash: EntityUnsplash) {
        daoUnsplash.insert(entityUnsplash = entityUnsplash)
    }

    override suspend fun deleteEntityUnsplash(id: Int) {
        daoUnsplash.deleteEntityUnsplash(id = id)
    }
}