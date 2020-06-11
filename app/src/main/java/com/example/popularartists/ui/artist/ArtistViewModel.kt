package com.example.popularartists.ui.artist

import android.app.Application
import androidx.lifecycle.liveData
import com.example.popularartists.data.Repository
import com.example.popularartists.data.network.letToSuccess
import com.example.popularartists.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers

class ArtistViewModel (
    application: Application,
    private val repository: Repository
) : BaseViewModel(application) {
    fun getTopAlbumsByArtist(artistName: String) = liveData(Dispatchers.IO) {
        emit(repository.getTopAlbumsByArtist(artistName).letToSuccess {
            it.sortedBy { artist ->
                artist.name
            }
        })
    }
}