package com.chahine.showhive.home.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chahine.showhive.home.R
import com.chahine.showhive.home.databinding.ItemProfileUserCardBinding
import javax.inject.Inject

class ProfileAdapter @Inject constructor() : ListAdapter<ProfileItem, RecyclerView.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ProfileItem>() {
            override fun areItemsTheSame(oldItem: ProfileItem, newItem: ProfileItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ProfileItem, newItem: ProfileItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    class UserCardHolder(private val binding: ItemProfileUserCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProfileItem.UserCard) = with(binding) {
            Glide.with(binding.avatar.context)
                .load(item.user.images?.avatar?.full)
                .circleCrop()
                .placeholder(R.drawable.avatar_placeholder)
                .into(avatar)

            name.text = if (!item.user.name.isNullOrBlank()) item.user.name else item.user.username
            location.text = item.user.location
            location.isVisible = !item.user.location.isNullOrBlank()
            about.text = item.user.about
            about.isVisible = !item.user.about.isNullOrBlank()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProfileUserCardBinding.inflate(inflater, parent, false)
        return UserCardHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserCardHolder).bind(getItem(holder.absoluteAdapterPosition) as ProfileItem.UserCard)
    }
}
