package com.example.popularartists.ui.album

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.example.popularartists.R
import com.example.popularartists.data.models.AlbumWithTracks
import com.example.popularartists.data.network.DefaultObserver
import com.example.popularartists.data.network.ResultObject
import com.example.popularartists.databinding.FragmentAlbumBinding
import com.example.popularartists.setImage
import com.example.popularartists.ui.album.adapter.TrackAdapter
import com.example.popularartists.ui.artist.adapter.ItemAlbumActionListener
import com.example.popularartists.ui.base.BaseFragment
import com.example.popularartists.ui.popularArtists.PopularArtistsFragmentDirections
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class AlbumFragment : BaseFragment<FragmentAlbumBinding>(), ItemAlbumActionListener {

//    companion object {
//        const val NAME_ARTIST = "NAME_ARTIST"
//        const val ARTIST_FRAGMENT_TAG = "ArtistFragment"
//        fun newInstance(name: String): ArtistFragment {
//            val fragment = ArtistFragment()
//            val bundle = Bundle()
//            bundle.putString(NAME_ARTIST, name)
//            fragment.arguments = bundle
//            return fragment
//        }
//    }

    override val contentLayoutId = R.layout.fragment_album
//    override var title = R.string.popular_artists_title

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
    }

    override fun setupBinding(binding: FragmentAlbumBinding) {
        super.setupBinding(binding)
        binding.itemsRecyclerView.adapter = trackAdapter
        arguments?.apply {
            val fromBundle = AlbumFragmentArgs.fromBundle(this)
            setupAlbum(fromBundle.nameAlbum, fromBundle.nameArtist)
//           getString(NAME_ARTIST)?.also {
//               setupTopAlbumsByArtist(it)
//           }
        }
    }

    private fun setupAlbum(albumName: String, nameArtist: String) {
        viewModel.getAlbum(nameArtist, albumName).observe(
            this,
            DefaultObserver<AlbumWithTracks, ResultObject<AlbumWithTracks>>()
                .handleSuccess {
                    it.getResult()?.apply {
                        binding.albumImageView.setImage(Uri.parse(image))
                        trackAdapter.setItems(tracks)
                    }
                }
        )
    }

    override fun onClick(name: String) {
       findNavController().navigate(PopularArtistsFragmentDirections.actionPopularArtistsFragmentToArtistFragment(name))
    }
}