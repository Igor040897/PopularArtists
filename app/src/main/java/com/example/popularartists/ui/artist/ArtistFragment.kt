package com.example.popularartists.ui.artist

import android.content.Context
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.example.popularartists.R
import com.example.popularartists.data.models.Album
import com.example.popularartists.data.network.DefaultObserver
import com.example.popularartists.data.network.ResultObject
import com.example.popularartists.databinding.FragmentArtistBinding
import com.example.popularartists.ui.artist.adapter.ItemAlbumActionListener
import com.example.popularartists.ui.artist.adapter.AlbumsAdapter
import com.example.popularartists.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ArtistFragment : BaseFragment<FragmentArtistBinding>(), ItemAlbumActionListener {

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

    override val contentLayoutId = R.layout.fragment_artist
//    override var title = R.string.popular_artists_title
    lateinit var nameArtist : String

    @Inject
    lateinit var viewModel: ArtistViewModel
    lateinit var albumsAdapter: AlbumsAdapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        albumsAdapter = AlbumsAdapter()
        albumsAdapter.itemAlbumActionListener = this
    }

    override fun setupBinding(binding: FragmentArtistBinding) {
        super.setupBinding(binding)
        binding.itemsRecyclerView.adapter = albumsAdapter
        arguments?.apply {
            val fromBundle = ArtistFragmentArgs.fromBundle(this)
            nameArtist = fromBundle.nameArtist
            setupTopAlbumsByArtist(nameArtist)
//           getString(NAME_ARTIST)?.also {
//               setupTopAlbumsByArtist(it)
//           }
        }
    }

    private fun setupTopAlbumsByArtist(artistName: String) {
        viewModel.getTopAlbumsByArtist(artistName).observe(
            this,
            DefaultObserver<List<Album>, ResultObject<List<Album>>>()
                .handleSuccess {
                    it.getResult()?.apply {
                        albumsAdapter.setItems(this)
                    }
                }
                .handleConnection {

                }
                .handleError {

                }
        )
    }

    override fun onClick(albumName: String) {
       findNavController().navigate(ArtistFragmentDirections.actionArtistFragmentToAlbumFragment(albumName, nameArtist))
    }
}