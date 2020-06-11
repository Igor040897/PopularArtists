package com.example.popularartists.ui.artist.adapter

import android.net.Uri
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.popularartists.data.models.Album

class ItemAlbumViewModel : ViewModel() {

    val albumField = ObservableField<Uri>()
    val nameField = ObservableField<String>()
    val playcountField = ObservableField<String>()

    fun start(album: Album) {
        albumField.set(Uri.parse(album.image))
        nameField.set(album.name)
        playcountField.set(album.playcount.toString())
    }
}