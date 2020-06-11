package com.example.popularartists.ui.popularArtists.adapter

import android.content.Context
import android.net.Uri
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.popularartists.R
import com.example.popularartists.data.models.Artist

class ItemArtistViewModel : ViewModel() {

    val avatarField = ObservableField<Uri>()
    val nameField = ObservableField<String>()
    val listenersField = ObservableField<String>()

    fun start(artist: Artist, context: Context) {
        val listeners = context.resources.getString(R.string.listeners, artist.listeners)

        avatarField.set(Uri.parse(artist.image))
        nameField.set(artist.name)
        listenersField.set(listeners)
    }
}