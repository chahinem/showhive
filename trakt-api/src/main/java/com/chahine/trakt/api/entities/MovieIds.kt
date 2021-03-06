package com.chahine.trakt.api.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieIds(
    val trakt: Int?,
    val imdb: Int?,
    val tmdb: Int?,
    val slug: String?
)
