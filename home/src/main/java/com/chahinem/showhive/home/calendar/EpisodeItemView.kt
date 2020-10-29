package com.chahinem.showhive.home.calendar

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.chahinem.showhive.home.R
import com.chahinem.trakt.entities.CalendarShowEntry
import javax.inject.Inject

class EpisodeItemView {
    class Delegate @Inject constructor() : CalendarAdapter.Delegate {

        override fun layoutId() = R.layout.item_calendar_episode

        override fun create(parent: ViewGroup) = Holder(itemView(parent))

        override fun bind(item: CalendarAdapter.Item, holder: ViewHolder) {
            if (holder is Holder && item is Item) {
                holder.bind(item)
            }
        }
    }

    class Holder(itemView: View) : ViewHolder(itemView) {

        private val showTitle = itemView.findViewById<TextView>(R.id.showTitle)
        private val episodeNumber = itemView.findViewById<TextView>(R.id.episodeNumber)
        private val episodeTitle = itemView.findViewById<TextView>(R.id.episodeTitle)

        fun bind(item: Item) {
            showTitle.text = item.entry.show.title
            episodeNumber.text = itemView.resources.getString(
                R.string.episode_number_format,
                item.entry.episode.season,
                item.entry.episode.number
            )
            episodeTitle.text = item.entry.episode.title
        }
    }

    class Item(val entry: CalendarShowEntry) : CalendarAdapter.Item {
        override fun itemViewType() = CalendarAdapter.EPISODE
    }
}
