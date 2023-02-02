package com.iugome.mytesttask.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface DaoUnsplash {
    @Insert
    suspend fun insert(entityUnsplash: EntityUnsplash)
    @Query("SELECT * FROM EntityUnsplash")
    suspend fun getEntityUnsplash():List<EntityUnsplash>
    @Query("DELETE FROM EntityUnsplash WHERE id = :id")
    suspend fun deleteEntityUnsplash(id:Int)
}