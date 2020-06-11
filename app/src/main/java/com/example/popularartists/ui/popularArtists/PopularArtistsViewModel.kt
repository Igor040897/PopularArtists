package com.example.popularartists.ui.popularArtists

import android.app.Application
import androidx.lifecycle.liveData
import com.example.popularartists.data.Repository
import com.example.popularartists.data.network.letToSuccess
import com.example.popularartists.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers

class PopularArtistsViewModel(
    application: Application,
    private val repository: Repository
) : BaseViewModel(application) {

    fun getTopArtistByCountry(country: String) = liveData(Dispatchers.IO) {
        emit(repository.getTopArtistByCountry(country).letToSuccess {
           //todo use TreeSet
            it.sortedBy { artist ->
                artist.name
            }
        })
    }
}