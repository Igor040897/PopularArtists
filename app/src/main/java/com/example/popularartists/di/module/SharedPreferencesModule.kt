package com.example.popularartists.di.module

import android.app.Application
import android.content.Context
import com.example.popularartists.data.preferences.SharedStorageManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val STORAGE_NAME = "PREFERENCES_STORAGE"

@Module
class SharedPreferencesModule {

    @Provides
    @Singleton
    fun provideSharedStorageManager(application: Application): SharedStorageManager {
        val sharedPreferences = application.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)
        return SharedStorageManager.getInstance(sharedPreferences)
    }
}