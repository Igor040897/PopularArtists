package com.example.popularartists.data

import com.example.popularartists.data.dataBase.DbStorageManager
import com.example.popularartists.data.models.Location
import com.example.popularartists.data.models.ServerError
import com.example.popularartists.data.network.*
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Call
import java.io.IOException

class RepositoryImpl(
    private val dbStorageManager: DbStorageManager,
    private val api: PopularArtistService
) : Repository {

    override suspend fun topArtistByCountry(country: String) = api.topArtistByCountry(country)
        .runRequest()
        .applyToSuccess {

        }

    private suspend fun <T : Any?> Call<T>.runRequest(): ResultObject<T> =
        withContext(Dispatchers.IO) {
            try {
//                if (!connectivityState.isConnected()) {
//                    return@withContext ConnectionError()
//                }
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
            api: PopularArtistService
        ): Repository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: RepositoryImpl(dbStorageManager, api).also { INSTANCE = it }
            }
        }
    }
}