package com.example.popularartists.ui.popularArtists.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.popularartists.R
import com.example.popularartists.data.models.Artist
import com.example.popularartists.databinding.ItemArtistBinding
import com.example.popularartists.inflateView

class PopularArtistsAdapter : RecyclerView.Adapter<PopularArtistsAdapter.ArtistVH>() {

    var itemArtistActionListener: ItemArtistActionListener? = null
    val items: ArrayList<Artist> = ArrayList()

    fun setItems(artists: List<Artist>) {
        items.clear()
        items.addAll(artists)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistVH {
        val binding = ItemArtistBinding.bind(parent.inflateView(R.layout.item_artist))
        binding.viewModel =
            ItemArtistViewModel()
        return ArtistVH(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ArtistVH, position: Int) {
        val artistItem = items[position]
        holder.bind(artistItem)
    }

    inner class ArtistVH(private val binding: ItemArtistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemArtistActionListener?.onArtistClick(items[adapterPosition].name)
            }
        }

        fun bind(item: Artist) {
            binding.viewModel?.start(item, binding.root.context)
        }
    }
}