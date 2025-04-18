package com.pivnoydevelopment.common.data.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pivnoydevelopment.common.data.db.dao.FavoritesDao
import com.pivnoydevelopment.common.data.db.entity.FavoritesCourseEntity

@Database(version = 1, entities = [FavoritesCourseEntity::class])
abstract class AppDatabase : RoomDatabase(){

    abstract fun favoritesDao(): FavoritesDao
}