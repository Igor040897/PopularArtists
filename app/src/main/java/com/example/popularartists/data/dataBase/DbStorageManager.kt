package com.example.popularartists.data.dataBase

import com.example.popularartists.data.models.Album
import com.example.popularartists.data.models.Artist
import com.example.popularartists.data.models.Track

class DbStorageManager private constructor(
    private val appDataBase: AppDataBase
) {

    fun savePopularArtists(artists: List<Artist>) {
        appDataBase.artistsDao().insertPopularArtists(artists)
    }

    fun getPopularArtists() =
        appDataBase.artistsDao().getPopularArtists()

    fun saveTopAlbumsArtist(artist: String, albums: List<Album>) {
        appDataBase.albumsDao().insertTopAlbumsArtist(artist, albums)
    }

    fun getTopAlbumsByArtist(nameArtist: String) =
        appDataBase.albumsDao().getTopAlbumsByArtist(nameArtist)

    fun saveTracks(albumName: String, tracks: List<Track>) {
        appDataBase.albumsDao().insertTracks(albumName, tracks)
    }

    fun getAlbum(albumName: String) =
        appDataBase.albumsDao().getAlbumWithTracks(albumName)


    companion object {

        @Volatile
        private var INSTANCE: DbStorageManager? = null

        fun getInstance(appDataBase: AppDataBase): DbStorageManager {
            return INSTANCE
                ?: synchronized(this) {
                INSTANCE
                    ?: DbStorageManager(
                        appDataBase
                    ).also { INSTANCE = it }
            }
        }
    }
}