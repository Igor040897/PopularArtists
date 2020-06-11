package com.example.popularartists.di.module

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.popularartists.data.network.ConnectivityDispatcher
import com.example.popularartists.data.network.ConnectivityState
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ConnectivityStateModule {
    @Provides
    @Singleton
    fun provideConnectivityManager(application: Application): ConnectivityManager {
        return application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @Singleton
    fun provideConnectivityState(connectivityManager: ConnectivityManager): ConnectivityState {
        return ConnectivityDispatcher.getInstance(connectivityManager)
    }
}