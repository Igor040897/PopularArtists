package com.example.popularartists.di.module.ui

import androidx.lifecycle.ViewModelProvider
import com.example.popularartists.di.module.RepositoryModule
import com.example.popularartists.di.module.ViewModelFactory
import com.example.popularartists.ui.main.MainActivity
import com.example.popularartists.ui.main.MainActivityViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [RepositoryModule::class])
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [MainActivityViewModelModule::class])
    abstract fun providesMainActivity(): MainActivity
}

@Module
class MainActivityViewModelModule {
    @Provides
    fun postAddItemViewModel(
        viewModelFactory: ViewModelFactory,
        activity: MainActivity
    ): MainActivityViewModel {
        return ViewModelProvider(activity, viewModelFactory)[MainActivityViewModel::class.java]
    }
}