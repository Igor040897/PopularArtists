package com.example.popularartists.ui.popularArtists

import android.content.Context
import android.os.Bundle
import com.example.popularartists.R
import com.example.popularartists.data.models.Artist
import com.example.popularartists.data.network.DefaultObserver
import com.example.popularartists.data.network.ResultObject
import com.example.popularartists.databinding.FragmentPopularArtistsBinding
import com.example.popularartists.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PopularArtistsFragment : BaseFragment<FragmentPopularArtistsBinding>() {

    companion object {
        fun newInstance() = PopularArtistsFragment()
    }

    override val contentLayoutId = R.layout.fragment_popular_artists
    override var title = R.string.popular_artists_title

    lateinit var artistsAdapter : PopularArtistsAdapter

    @Inject
    lateinit var viewModel: PopularArtistsViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        artistsAdapter = PopularArtistsAdapter()
    }

    override fun setupBinding(binding: FragmentPopularArtistsBinding) {
        super.setupBinding(binding)
        binding.itemsRecyclerView.adapter = artistsAdapter
        viewModel.apply {
            getTopArtistByCountry("Ukraine").observe(
                this@PopularArtistsFragment,
                DefaultObserver<List<Artist>, ResultObject<List<Artist>>>()
                    .handleSuccess {
                        it.getResult()?.apply {
                            artistsAdapter.setItems(this)
                        }
                    }
                    .handleConnection {

                    }
                    .handleError {

                    }
            )
        }
    }
}
