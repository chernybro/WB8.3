package com.chernybro.wb53.data.storage.entities.favourites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chernybro.wb53.domain.data.FavouriteCat
import com.chernybro.wb53.domain.data.Image

@Entity
data class FavouriteCatEntity(
    @PrimaryKey
    val id: Int,
    val imageUrl: String
)

fun FavouriteCatEntity.toFavouriteCat(): FavouriteCat {
    return FavouriteCat(
        id = id,
        image = Image(
            id = id.toString(),
            url = imageUrl
        )
    )
}
