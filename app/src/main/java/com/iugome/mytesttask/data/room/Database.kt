package com.iugome.mytesttask.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [EntityUnsplash::class], version = 1 , exportSchema = false)
abstract class Database: RoomDatabase() {
    abstract fun getDataBase():DaoUnsplash
}