package com.example.popularartists.data.models

import androidx.room.Ignore
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class AlbumWithTracks : Album {
    @Ignore
    var tracks: List<Track>

    constructor() : this("", -1, "", arrayListOf())

    constructor(album: Album) : this(album.name, album.playcount, album.image, arrayListOf())

    constructor(
        name: String,
        playcount: Long,
        image: String,
        tracks: List<Track>
    ) : super(
        name,
        playcount,
        image
    ) {
        this.tracks = tracks
    }

    class AlbumsWithTracksJsonDeserializer : JsonDeserializer<AlbumWithTracks> {
        companion object{
            private const val ALBUM = "album"
            private const val NAME = "name"
            private const val PLAY_COUNT = "playcount"
            private const val IMAGE = "image"
            private const val TEXT = "#text"
            private const val TRACKS = "tracks"
            private const val TRACK = "track"
            private const val DURATION = "duration"
        }

        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): AlbumWithTracks {
            val albumWithTracks = AlbumWithTracks()
            json?.also {
                val albumJsonObject = it.asJsonObject.get(ALBUM).asJsonObject

                val name = albumJsonObject.get(NAME).asString
                val playcount = albumJsonObject.get(PLAY_COUNT).asLong
                val albumUrl = albumJsonObject.get(IMAGE).asJsonArray.last().asJsonObject.get(TEXT).asString

                val jsonArray = albumJsonObject.get(TRACKS).asJsonObject.get(TRACK).asJsonArray
                val tracks = arrayListOf<Track>()
                jsonArray.forEach {
                    val trackJsonObject = it.asJsonObject
                    val name = trackJsonObject.get(NAME).asString
                    val duration = trackJsonObject.get(DURATION).asLong
                    tracks.add(Track(name, duration))
                }

                albumWithTracks.name = name
                albumWithTracks.playcount = playcount
                albumWithTracks.image = albumUrl
                albumWithTracks.tracks = tracks
            }
            return albumWithTracks
        }
    }
}