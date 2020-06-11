package com.example.popularartists.data

import com.example.popularartists.BuildConfig
import com.example.popularartists.data.dataBase.DbStorageManager
import com.example.popularartists.data.models.ServerError
import com.example.popularartists.data.network.*
import com.example.popularartists.data.preferences.SharedStorageManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Call
import java.io.IOException

class RepositoryImpl private constructor(
    private val dbStorageManager: DbStorageManager,
    private val sharedStorageManager: SharedStorageManager,
    private val api: PopularArtistService,
    private val connectivityState: ConnectivityState
) : Repository {

    override suspend fun getTopArtistByCountry(country: String) =
        if (useNetworkData()) {
            api.getTopArtistByCountry(country)
                .runRequest()
                .applyToSuccess {
                    dbStorageManager.savePopularArtists(it)
                }
        } else {
            ResultObject.SuccessResult(dbStorageManager.getPopularArtists())
        }

    override suspend fun getTopAlbumsByArtist(artist: String) =
        if (useNetworkData()) {
            api.getTopAlbumsByArtist(artist)
                .runRequest()
                .applyToSuccess {
                    dbStorageManager.saveTopAlbumsArtist(artist, it)
                }
        } else {
            ResultObject.SuccessResult(dbStorageManager.getTopAlbumsByArtist(artist))
        }

    override suspend fun getAlbum(artist: String, albumName: String) =
        if (useNetworkData()) {
            api.getAlbum(artist, albumName)
                .runRequest()
                .applyToSuccess {
                    dbStorageManager.saveTracks(albumName, it.tracks)
                }
        } else {
            ResultObject.SuccessResult(dbStorageManager.getAlbum(albumName))
        }

    override fun changeDataMode(isNetwork: Boolean) {
        sharedStorageManager.saveDataMode(isNetwork)
    }

    private fun useNetworkData(): Boolean {
        val useDataMode = BuildConfig.FLAVOR == "dev"

        return if (useDataMode) {
            sharedStorageManager.isNetworkDataMode()
        } else connectivityState.isConnected()
    }

    //todo add to CallAdapter Retrofit2
    private suspend fun <T : Any?> Call<T>.runRequest(): ResultObject<T> =
        withContext(Dispatchers.IO) {
            try {
                val response = execute()
                if (response.isSuccessful) {
                    ResultObject.SuccessResult(response.body())
                } else {
                    responseBodyToError<T>(response.errorBody(), response.code())
                }
            } catch (e: IOException) {
                ResultObject.ErrorResult(e)
            }
        }

    private fun <T : Any?> responseBodyToError(errorBody: ResponseBody?, code: Int): ResponseError {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(ServerError::class.java, ErrorDeserializer())
            .create()

        var errorMessage = "Unexpected error"
        errorBody?.run {
            val errorJson = string()
            try {
                val error = gson.fromJson(errorJson, ServerError::class.java)
                error?.message?.run {
                    errorMessage = this
                }
            } catch (e: JsonSyntaxException) {
                errorMessage = errorJson
            }
        }
        return ResponseError(code, Throwable(errorMessage))
    }

    companion object {
        @Volatile
        private var INSTANCE: RepositoryImpl? = null

        fun getInstance(
            dbStorageManager: DbStorageManager,
            sharedStorageManager: SharedStorageManager,
            api: PopularArtistService,
            connectivityManager: ConnectivityState
        ): Repository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: RepositoryImpl(
                    dbStorageManager,
                    sharedStorageManager,
                    api,
                    connectivityManager
                ).also { INSTANCE = it }
            }
        }
    }
}