package com.example.popularartists.ui.album

import android.content.Context
import android.net.Uri
import android.os.Bundle
import com.example.popularartists.R
import com.example.popularartists.databinding.FragmentAlbumBinding
import com.example.popularartists.observe
import com.example.popularartists.setImage
import com.example.popularartists.ui.album.adapter.TrackAdapter
import com.example.popularartists.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class AlbumFragment : BaseFragment<FragmentAlbumBinding>() {

    override val contentLayoutId = R.layout.fragment_album

    @Inject
    lateinit var viewModel: AlbumViewModel
    lateinit var trackAdapter: TrackAdapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trackAdapter = TrackAdapter()
        arguments?.apply {
            activity?.toolbar?.title = AlbumFragmentArgs.fromBundle(this).nameAlbum
        }
    }

    override fun setupBinding(binding: FragmentAlbumBinding) {
        super.setupBinding(binding)
        binding.itemsRecyclerView.adapter = trackAdapter
        arguments?.apply {
            val fromBundle = AlbumFragmentArgs.fromBundle(this)
            setupAlbum(fromBundle.nameAlbum, fromBundle.nameArtist)
        }
    }

    private fun setupAlbum(albumName: String, nameArtist: String) {
        viewLifecycleOwner.observe(viewModel.getAlbum(nameArtist, albumName), {
            binding.albumImageView.setImage(Uri.parse(it.image))
            trackAdapter.setItems(it.tracks)
        })
    }
}