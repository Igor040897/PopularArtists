package com.example.popularartists.data

import com.example.popularartists.data.models.Album
import com.example.popularartists.data.models.AlbumWithTracks
import com.example.popularartists.data.models.Artist
import com.example.popularartists.data.network.ResultObject

interface Repository {

    suspend fun getTopArtistByCountry(country: String): ResultObject<List<Artist>>

    suspend fun getTopAlbumsByArtist(artist: String): ResultObject<List<Album>>

    suspend fun getAlbum(artist: String, albumName: String): ResultObject<AlbumWithTracks>

}