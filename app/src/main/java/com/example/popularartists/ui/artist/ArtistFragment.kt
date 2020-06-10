package com.example.popularartists.ui.artist

import android.content.Context
import com.example.popularartists.R
import com.example.popularartists.databinding.FragmentPopularArtistsBinding
import com.example.popularartists.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ArtistFragment : BaseFragment<FragmentPopularArtistsBinding>() {

    companion object {
        fun newInstance() = ArtistFragment()
    }

    override val contentLayoutId = R.layout.fragment_artist
//    override var title = R.string.popular_artists_title

    @Inject
    lateinit var viewModel: ArtistViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}