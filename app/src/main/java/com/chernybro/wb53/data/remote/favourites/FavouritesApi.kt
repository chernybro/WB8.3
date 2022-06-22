package com.chernybro.wb53.data.remote.favourites

import com.chernybro.wb53.domain.data.FavouriteCat

interface FavouritesApi {

    suspend fun getFavourites(): List<FavouriteCat>

    suspend fun deleteFavourite(id: Int)

    suspend fun postFavourite(id: String)
}