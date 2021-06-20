package com.chahine.trakt.api.entities

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.time.ZonedDateTime

class SettingsTest {

    private val moshi = com.chahine.trakt.api.MoshiFactory().make()

    private val testClass = Settings::class.java

    @Test
    fun `deserializes Settings`() {
        val actual = moshi.adapter(testClass).fromJson(
            """
            {
              "user": {
                "username": "justin",
                "private": false,
                "name": "Justin Nemeth",
                "vip": true,
                "vip_ep": false,
                "ids": {
                  "slug": "justin",
                  "uuid": "b6589fc6ab0dc82cf12099d1c2d40ab994e8410c"
                },
                "joined_at": "2010-09-25T17:49:25.000Z",
                "location": "San Diego, CA",
                "about": "Co-founder of trakt.",
                "gender": "male",
                "age": 32,
                "images": {
                  "avatar": {
                    "full": "https://secure.gravatar.com/avatar/30c2f0dfbc39e48656f40498aa871e33?r=pg&s=256"
                  }
                },
                "vip_og": true,
                "vip_years": 5
              },
              "account": {
                "timezone": "America/Los_Angeles",
                "date_format": "mdy",
                "time_24hr": false,
                "cover_image": "https://walter.trakt.tv/images/movies/000/001/545/fanarts/original/0abb604492.jpg"
              },
              "connections": {
                "facebook": true,
                "twitter": true,
                "google": true,
                "tumblr": false,
                "medium": false,
                "slack": false,
                "apple": false
              },
              "sharing_text": {
                "watching": "I'm watching [item]",
                "watched": "I just watched [item]",
                "rated": "[item] [stars]"
              }
            }
            """.trimIndent()
        )

        assertNotNull(actual)
    }

    @Test
    fun `serialize and deserializes Settings`() {
        val expected = Settings(
            user = User(
                username = "justin",
                isPrivate = false,
                name = "Justin Nemeth",
                vip = true,
                vipEp = false,
                ids = UserIds("justin"),
                joinedAt = ZonedDateTime.parse("2010-09-25T17:49:25.000Z"),
                location = "San Diego, CA",
                about = "Co-founder of trakt.",
                gender = "male",
                age = 32,
                images = Images(ImageSizes("https://secure.gravatar.com/avatar/30c2f0dfbc39e48656f40498aa871e33?r=pg&s=256")),
            ),
            account = Account(
                "America/Los_Angeles",
                "mdy",
                false,
                "https://walter.trakt.tv/images/movies/000/001/545/fanarts/original/0abb604492.jpg"
            )
        )

        val json = moshi.adapter(testClass).toJson(expected)
        val actual = moshi.adapter(testClass).fromJson(json)

        assertEquals(expected, actual)
    }
}
