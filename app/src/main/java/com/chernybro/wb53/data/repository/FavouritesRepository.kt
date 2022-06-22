package com.chernybro.wb53.data.repository

import com.chernybro.wb53.domain.data.FavouriteCat
import com.chernybro.wb53.utils.Resource

interface FavouritesRepository {

    suspend fun getFavourites(): Resource<List<FavouriteCat>>
    suspend fun insertFavourite(id: String): Resource<Unit>
    suspend fun deleteFavourite(id: Int): Resource<Unit>
    suspend fun updateRemote(): Resource<List<FavouriteCat>>
}