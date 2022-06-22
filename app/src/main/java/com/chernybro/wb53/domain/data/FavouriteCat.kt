package com.chernybro.wb53.domain.data

import com.chernybro.wb53.data.storage.entities.favourites.FavouriteCatEntity
import kotlinx.serialization.Serializable

@Serializable
data class FavouriteCat(
    val id: Int,
    val image: Image
)

@Serializable
data class Image(
    val id: String,
    val url: String
)

fun FavouriteCat.toFavouriteCatEntity(): FavouriteCatEntity {
    return FavouriteCatEntity(
        id = id,
        imageUrl = image.url
    )
}
