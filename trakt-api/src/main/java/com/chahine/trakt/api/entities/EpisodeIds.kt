package com.chahine.trakt.api.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EpisodeIds(
    val trakt: Int?,
    val imdb: String?,
    val tmdb: Int?,
    val tvdb: Int?,
    val tvrage: Int?
)
