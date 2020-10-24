package com.chahinem.tmdb.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class BaseKeyword(
    val id: Int? = null,
    val name: String? = null
)
