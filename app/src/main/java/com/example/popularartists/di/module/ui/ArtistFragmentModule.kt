package com.example.popularartists.di.module.ui

import androidx.lifecycle.ViewModelProvider
import com.example.popularartists.di.module.RepositoryModule
import com.example.popularartists.di.module.ViewModelFactory
import com.example.popularartists.ui.artist.ArtistFragment
import com.example.popularartists.ui.artist.ArtistViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [RepositoryModule::class])
abstract class ArtistFragmentModule {
    @ContributesAndroidInjector(modules = [ArtistViewModelModule::class])
    abstract fun providesItemsListFragment(): ArtistFragment
}

@Module
class ArtistViewModelModule {
    @Provides
    fun postAddItemViewModel(
        viewModelFactory: ViewModelFactory,
        fragment: ArtistFragment
    ): ArtistViewModel {
        return ViewModelProvider(fragment, viewModelFactory)[ArtistViewModel::class.java]
    }
}