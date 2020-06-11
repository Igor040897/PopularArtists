package com.example.popularartists.ui.main

import android.app.Application
import com.example.popularartists.data.Repository
import com.example.popularartists.ui.base.BaseViewModel

class MainActivityViewModel (
    application: Application,
    private val repository: Repository
) : BaseViewModel(application) {

    fun changeDataMode(isNetwork: Boolean) {
        repository.changeDataMode(isNetwork)
    }
}