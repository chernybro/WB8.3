package com.chernybro.wb53.domain.data

import kotlinx.serialization.Serializable

@Serializable
data class Cat(
    val id: String,
    val url: String
)

