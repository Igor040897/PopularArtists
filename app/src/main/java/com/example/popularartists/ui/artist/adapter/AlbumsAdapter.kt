package com.example.popularartists.ui.artist.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.popularartists.R
import com.example.popularartists.data.models.Album
import com.example.popularartists.databinding.ItemAlbumBinding
import com.example.popularartists.inflateView

class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.AlbumVH>() {

    var itemAlbumActionListener: ItemAlbumActionListener? = null
    val items: ArrayList<Album> = ArrayList()

    fun setItems(albums: List<Album>) {
        items.clear()
        items.addAll(albums)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumVH {
        val binding = ItemAlbumBinding.bind(parent.inflateView(R.layout.item_album))
        binding.viewModel = ItemAlbumViewModel()
        return AlbumVH(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: AlbumVH, position: Int) {
        val albumItem = items[position]
        holder.bind(albumItem)
    }

    inner class AlbumVH(private val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemAlbumActionListener?.onClick(items[adapterPosition].name)
            }
        }

        fun bind(item: Album) {
            binding.viewModel?.start(item)
        }
    }
}
