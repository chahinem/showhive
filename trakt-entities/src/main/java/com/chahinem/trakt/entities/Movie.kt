package com.chahinem.trakt.entities

import com.squareup.moshi.JsonClass
import org.threeten.bp.LocalDate

@JsonClass(generateAdapter = true)
class Movie(
    val year: Int? = null,
    val ids: MovieIds? = null,
    val certification: String? = null,
    val tagline: String? = null,
    val released: LocalDate? = null,
    val runtime: Int? = null,
    val trailer: String? = null,
    val homepage: String? = null,
    val language: String? = null,
    val genres: List<String>? = null
)
