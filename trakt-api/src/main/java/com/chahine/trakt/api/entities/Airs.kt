package com.chahine.trakt.api.entities

import com.squareup.moshi.JsonClass
import java.time.DayOfWeek

@JsonClass(generateAdapter = true)
data class Airs(
    val day: DayOfWeek?,
    val time: String?,
    val timezone: String?
)
