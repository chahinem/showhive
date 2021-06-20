package com.chahine.trakt.api.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.ZonedDateTime

@JsonClass(generateAdapter = true)
data class User(
    val username: String?,
    @Json(name = "private") val isPrivate: Boolean?,
    val name: String?,
    val vip: Boolean?,
    @Json(name = "vip_ep") val vipEp: Boolean?,
    val ids: UserIds?,
    @Json(name = "joined_at") val joinedAt: ZonedDateTime?,
    val location: String?,
    val about: String?,
    val gender: String?,
    val age: Int?,
    val images: Images?,
)
