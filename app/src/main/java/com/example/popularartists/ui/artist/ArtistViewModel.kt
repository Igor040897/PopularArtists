package com.example.popularartists.ui.artist

import android.app.Application
import com.example.popularartists.data.Repository
import com.example.popularartists.ui.base.BaseViewModel

class ArtistViewModel (
    application: Application,
    private val repository: Repository
) : BaseViewModel(application) {
}