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
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): AlbumWithTracks {
            val albumWithTracks = AlbumWithTracks()
            json?.also {
                //todo move to const
                val albumJsonObject = it.asJsonObject.get("album").asJsonObject

                val name = albumJsonObject.get("name").asString
                val playcount = albumJsonObject.get("playcount").asLong
                val albumUrl = albumJsonObject.get("image").asJsonArray.last().asJsonObject.get("#text").asString

                val jsonArray = albumJsonObject.get("tracks").asJsonObject.get("track").asJsonArray
                val tracks = arrayListOf<Track>()
                jsonArray.forEach {
                    val trackJsonObject = it.asJsonObject
                    val name = trackJsonObject.get("name").asString
                    val duration = trackJsonObject.get("duration").asLong
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