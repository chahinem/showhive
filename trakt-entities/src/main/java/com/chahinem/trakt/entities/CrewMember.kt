package com.chahinem.trakt.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CrewMember(
    val job: String?,
    val movie: Movie?,
    val show: Show?,
    val person: Person?
)
