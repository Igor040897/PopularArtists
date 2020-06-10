package com.example.popularartists.di.module

import com.example.popularartists.BuildConfig
import com.example.popularartists.data.models.Artist
import com.example.popularartists.data.network.PopularArtistService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module(includes = [RetrofitBuilderModule::class])
class ApiModule {
    @Provides
    fun provideApi(retrofit: Retrofit): PopularArtistService {
        return retrofit.create(PopularArtistService::class.java)
    }
}

@Module(includes = [OkHttpModule::class, GsonModule::class])
class RetrofitBuilderModule {
    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, gson : Gson): Retrofit {
        val builder = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))

        return builder.build()

    }
}

@Module
class GsonModule {
    var artistsType: Type = object : TypeToken<List<@kotlin.jvm.JvmSuppressWildcards Artist>>() {}.type
    @Singleton
    @Provides
    fun providesGson() =  GsonBuilder()
        .registerTypeAdapter(artistsType, Artist.TopArtistsJsonDeserializer())
        .create()
}

@Module
class OkHttpModule {

    private val httpLoggingInterceptor =
        HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BODY }

    @Singleton
    @Provides
    fun providesOkHttpClient() = OkHttpClient().newBuilder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}