package com.chernybro.wb53.data.remote.favourites

import com.chernybro.wb53.data.remote.models.PostFavouriteRequest
import com.chernybro.wb53.domain.data.FavouriteCat
import com.chernybro.wb53.utils.Constants
import com.chernybro.wb53.utils.Resource
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class FavouritesApiImpl(
    private val httpClient: HttpClient
) : FavouritesApi {

    override suspend fun getFavourites(): List<FavouriteCat> {
        return httpClient.get<List<FavouriteCat>>(HttpRequestBuilder().apply {
                url(Constants.ENDPOINT_FAVOURITES)
                header("x-api-key", Constants.TOKEN)
            })
    }

    override suspend fun deleteFavourite(id: Int) {
        return httpClient.delete(HttpRequestBuilder().apply {
                url(Constants.ENDPOINT_FAVOURITES + "/$id")
                header("x-api-key", Constants.TOKEN)
            })
    }

    override suspend fun postFavourite(id: String) {
        return httpClient.post(HttpRequestBuilder().apply {
                url(Constants.ENDPOINT_FAVOURITES)
                header("x-api-key", Constants.TOKEN)
                body = PostFavouriteRequest(id, "user-id1")
                contentType(ContentType.Application.Json)
            })
    }
}
