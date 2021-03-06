package com.chahine.trakt.api.entities

import com.squareup.moshi.JsonClass
import java.time.LocalDate

@JsonClass(generateAdapter = true)
data class CalendarMovieEntry(
    val released: LocalDate?,
    val movie: Movie?
)
