package com.example.popularartists.data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.popularartists.data.dataBase.dao.AlbumsDao
import com.example.popularartists.data.dataBase.dao.ArtistsDao
import com.example.popularartists.data.models.Album
import com.example.popularartists.data.models.AlbumWithTracks
import com.example.popularartists.data.models.Artist
import com.example.popularartists.data.models.Track

const val NAME_DATABASE = "database-app"

const val VERSION_DATABASE_1 = 1
const val CURRENT_VERSION_DATABASE = VERSION_DATABASE_1

@Database(
    entities = [
        Artist::class,
        Album::class,
        Track::class
    ],
    version = CURRENT_VERSION_DATABASE,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun artistsDao(): ArtistsDao

    abstract fun albumsDao(): AlbumsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(
            context: Context
        ): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room
                    .databaseBuilder(context, AppDataBase::class.java, NAME_DATABASE)
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}