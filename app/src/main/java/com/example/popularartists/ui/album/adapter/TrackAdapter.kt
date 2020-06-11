package com.example.popularartists.ui.album.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.popularartists.R
import com.example.popularartists.data.models.Track
import com.example.popularartists.databinding.ItemTrackBinding
import com.example.popularartists.inflateView
import com.example.popularartists.ui.artist.adapter.ItemAlbumActionListener

class TrackAdapter : RecyclerView.Adapter<TrackAdapter.TrackVH>() {

    var itemAlbumActionListener: ItemAlbumActionListener? = null
    val items: ArrayList<Track> = ArrayList()

    fun setItems(tracks: List<Track>) {
        items.clear()
        items.addAll(tracks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackVH {
        val binding = ItemTrackBinding.bind(parent.inflateView(R.layout.item_track))
        return TrackVH(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: TrackVH, position: Int) {
        val trackItem = items[position]
        holder.bind(trackItem)
    }

    inner class TrackVH(private val binding: ItemTrackBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemAlbumActionListener?.onClick(items[adapterPosition].name)
            }
        }

        fun bind(item: Track) {
            val min = item.duration / 60
            val sec = item.duration % 60
            binding.trackTextView.text = binding.root.context.resources.getString(
                R.string.track_info,
                adapterPosition + 1,
                item.name,
                min,
                sec
            )
        }
    }
}

