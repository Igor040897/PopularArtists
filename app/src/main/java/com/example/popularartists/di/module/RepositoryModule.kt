package com.example.popularartists.di.module

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.popularartists.data.Repository
import com.example.popularartists.data.RepositoryImpl
import com.example.popularartists.data.dataBase.AppDataBase
import com.example.popularartists.data.dataBase.DbStorageManager
import com.example.popularartists.data.network.ConnectivityDispatcher
import com.example.popularartists.data.network.PopularArtistService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(application: Application, database: AppDataBase, api : PopularArtistService): Repository {
        //todo move connectivityStatus to module
        val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val connectivityStatus = ConnectivityDispatcher.getInstance(connectivityManager)
        val dbStorageManager = DbStorageManager.getInstance(database)
        return RepositoryImpl.getInstance(dbStorageManager, api, connectivityStatus)
    }
}