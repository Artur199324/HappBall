package com.iugome.mytesttask.domain.rrepository

import com.iugome.mytesttask.data.room.EntityUnsplash

interface DatabaseRepositoryInterface {
    suspend fun getEntityUnsplash():List<EntityUnsplash>
    suspend fun insertEntityUnsplash(entityUnsplash: EntityUnsplash)
    suspend fun deleteEntityUnsplash(id: Int)
}