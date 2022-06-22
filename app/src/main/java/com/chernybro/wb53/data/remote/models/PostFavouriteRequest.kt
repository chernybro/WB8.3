package com.chernybro.wb53.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostFavouriteRequest(
    @SerialName("image_id")
    val imageId: String,
    @SerialName("sub_id")
    val subId: String
)
