package com.iugome.mytesttask.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EntityUnsplash")
class EntityUnsplash(
    @ColumnInfo(name = "idUnsplash")
    var idUnsplash: String,
    @ColumnInfo(name = "createdAt")
    var createdAt: String,
    @ColumnInfo(name = "likes")
    var likes: String,
    @ColumnInfo(name = "linkPhoto")
    var linkPhoto: String,
    @ColumnInfo(name = "name")
    var name: String,
    @PrimaryKey(autoGenerate = true)
    var id :Int? = null
)