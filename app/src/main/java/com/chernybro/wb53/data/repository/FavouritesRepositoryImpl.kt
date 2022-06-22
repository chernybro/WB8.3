package com.chernybro.wb53.data.repository

import android.content.Context
import com.chernybro.wb53.R
import com.chernybro.wb53.data.remote.favourites.FavouritesApi
import com.chernybro.wb53.data.storage.dao.FavouriteCatDAO
import com.chernybro.wb53.data.storage.entities.favourites.toFavouriteCat
import com.chernybro.wb53.domain.data.FavouriteCat
import com.chernybro.wb53.domain.data.toFavouriteCatEntity
import com.chernybro.wb53.utils.Resource
import java.nio.channels.UnresolvedAddressException

class FavouritesRepositoryImpl(
    private val favouritesApi: FavouritesApi,
    private val favouriteCatDAO: FavouriteCatDAO,
    private val context: Context
) : FavouritesRepository {

    override suspend fun getFavourites(): Resource<List<FavouriteCat>> {
        return try {
            val databaseCats = favouriteCatDAO.getAll()
            if (databaseCats.isEmpty()) {
                updateRemote()
            } else {
                Resource.Success(data = databaseCats.map { it.toFavouriteCat() })
            }
        } catch (e: Exception) {
            try {
                val remoteCats = favouritesApi.getFavourites()
                Resource.Success(data = remoteCats)
            } catch (e: UnresolvedAddressException) {
                Resource.Error(message = context.resources.getString(R.string.error_internet_not_found))
            } catch (e: Exception) {
                Resource.Error(message = e.toString())
            }
        }
    }

    override suspend fun updateRemote(): Resource<List<FavouriteCat>> {
        return try {
            val remoteCats = favouritesApi.getFavourites()
            favouriteCatDAO.insertAll(remoteCats.map { it.toFavouriteCatEntity() })
            Resource.Success(data = remoteCats)
        } catch (e: UnresolvedAddressException) {
            Resource.Error(message = context.resources.getString(R.string.error_internet_not_found))
        } catch (e: Exception) {
            Resource.Error(message = e.toString())
        }
    }

    override suspend fun deleteFavourite(id: Int): Resource<Unit> {
        return try {
            favouritesApi.deleteFavourite(id)
            favouriteCatDAO.deleteAll()
            updateRemote()
            Resource.Success(data = null)
        } catch (e: UnresolvedAddressException) {
            Resource.Error(message = context.resources.getString(R.string.error_internet_not_found))
        } catch (e: Exception) {
            Resource.Error(message = e.toString())
        }
    }

    override suspend fun insertFavourite(id: String): Resource<Unit> {
        return try {
            favouritesApi.postFavourite(id)
            favouriteCatDAO.deleteAll()
            updateRemote()
            Resource.Success(data = null)
        } catch (e: UnresolvedAddressException) {
            Resource.Error(message = context.resources.getString(R.string.error_internet_not_found))
        } catch (e: Exception) {
            Resource.Error(message = e.toString())
        }
    }
}