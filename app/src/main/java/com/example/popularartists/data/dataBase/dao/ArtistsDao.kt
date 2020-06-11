package com.example.popularartists.data.dataBase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.popularartists.data.models.Artist

@Dao
abstract class ArtistsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPopularArtists(artists: List<Artist>)

    @Query("SELECT * FROM Artist")
    abstract fun getPopularArtists(): List<Artist>
}