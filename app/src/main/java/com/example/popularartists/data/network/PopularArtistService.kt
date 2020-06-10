package com.example.popularartists.data.network

import com.example.popularartists.data.models.Artist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularArtistService {

    @GET(GET_TOP_ARTIST_BY_COUNTRY)
    fun topArtistByCountry(@Query("country") country: String) : Call<List<Artist>>
}