package com.chahine.trakt.api.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Account(
    val timezone: String,
    @Json(name = "date_format") val dateFormat: String,
    @Json(name = "time_24hr") val time24hr: Boolean,
    @Json(name = "cover_image") val coverImage: String?,
)
