package com.example.popularartists.data.dataBase.dao

import androidx.room.*
import com.example.popularartists.data.models.Artist

@Dao
abstract class ArtistsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPopularArtists(artists: List<Artist>)

    //todo return LiveData
    @Query("SELECT * FROM Artist")
    abstract fun getPopularArtists(): List<Artist>
}