package com.example.popularartists.di.module.ui

import androidx.lifecycle.ViewModelProvider
import com.example.popularartists.di.module.RepositoryModule
import com.example.popularartists.di.module.ViewModelFactory
import com.example.popularartists.ui.album.AlbumFragment
import com.example.popularartists.ui.album.AlbumViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [RepositoryModule::class])
abstract class AlbumFragmentModule {
    @ContributesAndroidInjector(modules = [AlbumViewModelModule::class])
    abstract fun providesAlbumFragment(): AlbumFragment
}

@Module
class AlbumViewModelModule {
    @Provides
    fun postAlbumViewModel(
        viewModelFactory: ViewModelFactory,
        fragment: AlbumFragment
    ): AlbumViewModel {
        return ViewModelProvider(fragment, viewModelFactory)[AlbumViewModel::class.java]
    }
}