package com.chahine.tmdb.api.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Image(
    @Json(name = "file_path") val filePath: String? = null,
    val width: Int? = null,
    val height: Int? = null,
    @Json(name = "iso_639_1") val iso6391: String? = null,
    @Json(name = "aspect_ratio") val aspectRatio: Double? = null,
    @Json(name = "vote_average") val voteAverage: Double? = null,
    @Json(name = "vote_count") val voteCount: Int? = null
)
