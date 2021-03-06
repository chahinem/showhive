package com.chahine.trakt.api.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.ZonedDateTime

@JsonClass(generateAdapter = true)
data class CalendarShowEntry(
    @Json(name = "first_aired") val firstAired: ZonedDateTime,
    val episode: Episode,
    val show: Show
)
