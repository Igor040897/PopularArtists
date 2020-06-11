package com.example.popularartists.data.dataBase.dao

import androidx.room.*
import com.example.popularartists.data.models.Album
import com.example.popularartists.data.models.AlbumWithTracks
import com.example.popularartists.data.models.Track

@Dao
abstract class AlbumsDao {
    @Transaction
    open fun insertTopAlbumsArtist(artist: String, albums: List<Album>) {
        for (album in albums) {
            album.artistName = artist
        }
        insertAlbums(albums)
    }

    @Transaction
    open fun insertTracks(albumName: String, tracks: List<Track>) {
        for (track in tracks) {
            track.albumName = albumName
        }
        insertTracks(tracks)
    }

    @Transaction
    open fun getAlbumWithTracks(albumName: String): AlbumWithTracks {
        val albums = getAlbums(albumName)
        val tracks = getTracks(albumName)
        val albumWithTracks = AlbumWithTracks(albums)
        albumWithTracks.tracks = tracks
        return albumWithTracks
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAlbums(albums: List<Album>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTracks(tracks: List<Track>)

    @Query("SELECT * FROM Album WHERE artistName = :artistName")
    abstract fun getTopAlbumsByArtist(artistName: String): List<Album>

    @Query("SELECT * FROM Album WHERE name = :albumName")
    abstract fun getAlbums(albumName: String): Album

    @Query("SELECT * FROM Track WHERE albumName = :albumName")
    abstract fun getTracks(albumName: String): List<Track>
}