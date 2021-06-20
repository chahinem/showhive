package com.chahine.showhive.home.profile

import com.chahine.trakt.api.entities.User
import java.util.UUID

sealed class ProfileItem(val id: String = UUID.randomUUID().toString()) {
    data class UserCard(val user: User) : ProfileItem()
}
