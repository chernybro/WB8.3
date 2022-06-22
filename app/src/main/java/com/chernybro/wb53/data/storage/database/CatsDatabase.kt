package com.chernybro.wb53.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chernybro.wb53.data.storage.dao.FavouriteCatDAO
import com.chernybro.wb53.data.storage.entities.favourites.FavouriteCatEntity

@Database(entities = [FavouriteCatEntity::class], version = 1)
abstract class CatsDatabase : RoomDatabase() {
    abstract val favouritesCatsDAO: FavouriteCatDAO
}