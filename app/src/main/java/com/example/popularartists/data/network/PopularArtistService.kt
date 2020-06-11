package com.example.popularartists.data.network

import com.example.popularartists.data.models.Album
import com.example.popularartists.data.models.AlbumWithTracks
import com.example.popularartists.data.models.Artist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularArtistService {

    @GET(GET_TOP_ARTIST_BY_COUNTRY)
    fun getTopArtistByCountry(@Query("country") country: String): Call<List<Artist>>

    @GET(GET_TOP_ALBUMS_BY_ARTIST)
    fun getTopAlbumsByArtist(@Query("artist") artist: String): Call<List<Album>>

    @GET(GET_ALBUM)
    fun getAlbum(
        @Query("artist") artist: String,
        @Query("album") album: String
    ): Call<AlbumWithTracks>


}