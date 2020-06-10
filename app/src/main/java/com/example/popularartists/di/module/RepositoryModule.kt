package com.example.popularartists.di.module

import com.example.popularartists.data.Repository
import com.example.popularartists.data.RepositoryImpl
import com.example.popularartists.data.dataBase.AppDataBase
import com.example.popularartists.data.dataBase.DbStorageManager
import com.example.popularartists.data.network.PopularArtistService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(database: AppDataBase, api : PopularArtistService): Repository {
        val dbStorageManager = DbStorageManager.getInstance(database)
        return RepositoryImpl.getInstance(dbStorageManager, api)
    }
}