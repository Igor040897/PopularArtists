package com.example.popularartists.di.module.ui

import androidx.lifecycle.ViewModelProvider
import com.example.popularartists.di.module.RepositoryModule
import com.example.popularartists.di.module.ViewModelFactory
import com.example.popularartists.ui.popularArtists.PopularArtistsFragment
import com.example.popularartists.ui.popularArtists.PopularArtistsViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [RepositoryModule::class])
abstract class PopularArtistsFragmentModule {
    @ContributesAndroidInjector(modules = [PopularArtistsViewModelModule::class])
    abstract fun providesItemsListFragment(): PopularArtistsFragment
}

@Module
class PopularArtistsViewModelModule {
    @Provides
    fun postAddItemViewModel(
        viewModelFactory: ViewModelFactory,
        fragment: PopularArtistsFragment
    ): PopularArtistsViewModel {
        return ViewModelProvider(fragment, viewModelFactory)[PopularArtistsViewModel::class.java]
    }
}