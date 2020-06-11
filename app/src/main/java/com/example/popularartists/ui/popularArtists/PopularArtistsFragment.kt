package com.example.popularartists.ui.popularArtists

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.navigation.fragment.findNavController
import com.example.popularartists.R
import com.example.popularartists.databinding.FragmentPopularArtistsBinding
import com.example.popularartists.observe
import com.example.popularartists.ui.base.BaseFragment
import com.example.popularartists.ui.popularArtists.adapter.ItemArtistActionListener
import com.example.popularartists.ui.popularArtists.adapter.PopularArtistsAdapter
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PopularArtistsFragment : BaseFragment<FragmentPopularArtistsBinding>(),
    ItemArtistActionListener {

    override val contentLayoutId = R.layout.fragment_popular_artists

    lateinit var artistsAdapter: PopularArtistsAdapter

    @Inject
    lateinit var viewModel: PopularArtistsViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        artistsAdapter =
            PopularArtistsAdapter()
        artistsAdapter.itemArtistActionListener = this
    }

    override fun setupBinding(binding: FragmentPopularArtistsBinding) {
        super.setupBinding(binding)
        binding.itemsRecyclerView.adapter = artistsAdapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val itemAtPosition = parent.getItemAtPosition(position)
                setupTopArtistByCountry(itemAtPosition.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    private fun setupTopArtistByCountry(country: String) {
        viewLifecycleOwner.observe(viewModel.getTopArtistByCountry(country), {
                artistsAdapter.setItems(it)
            }
        )
    }

    override fun onArtistClick(artistName: String) {
        findNavController().navigate(
            PopularArtistsFragmentDirections.actionPopularArtistsFragmentToArtistFragment(
                artistName
            )
        )
    }
}
