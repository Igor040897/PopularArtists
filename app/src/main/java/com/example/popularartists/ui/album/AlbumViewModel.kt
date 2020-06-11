package com.example.popularartists.ui.album

import android.app.Application
import androidx.lifecycle.liveData
import com.example.popularartists.data.Repository
import com.example.popularartists.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers

class AlbumViewModel (
    application: Application,
    private val repository: Repository
) : BaseViewModel(application) {
    fun getAlbum(artistName: String, albumName: String) = liveData(Dispatchers.IO) {
        emit(repository.getAlbum(artistName, albumName))
    }
}