package com.chernybro.wb53.data.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.chernybro.wb53.data.storage.entities.favourites.FavouriteCatEntity

@Dao
interface FavouriteCatDAO {

    @Insert
    fun insertAll(favouritesCats : List<FavouriteCatEntity>)

    @Query("SELECT * FROM FavouriteCatEntity")
    fun getAll(): List<FavouriteCatEntity>

    @Query("DELETE FROM FavouriteCatEntity")
    fun deleteAll()
}