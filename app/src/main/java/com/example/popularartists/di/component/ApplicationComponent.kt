package com.example.popularartists.di.component

import android.app.Application
import com.example.popularartists.App
import com.example.popularartists.di.module.ApiModule
import com.example.popularartists.di.module.DataBaseModule
import com.example.popularartists.di.module.RepositoryModule
import com.example.popularartists.di.module.ViewModelFactoryModule
import com.example.popularartists.di.module.ui.AlbumFragmentModule
import com.example.popularartists.di.module.ui.ArtistFragmentModule
import com.example.popularartists.di.module.ui.PopularArtistsFragmentModule
import com.example.popularartists.di.module.ui.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApiModule::class,
        DataBaseModule::class,
        RepositoryModule::class,
        ViewModelFactoryModule::class,
        MainActivityModule::class,
        ArtistFragmentModule::class,
        PopularArtistsFragmentModule::class,
        AlbumFragmentModule::class
    ]
)
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: App)
}