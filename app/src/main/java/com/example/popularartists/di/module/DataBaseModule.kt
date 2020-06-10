package com.example.popularartists.di.module

import android.app.Application
import com.example.popularartists.data.dataBase.AppDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): AppDataBase {
        return AppDataBase.getInstance(application)
    }
}