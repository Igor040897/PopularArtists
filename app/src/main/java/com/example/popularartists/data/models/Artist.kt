package com.example.popularartists.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

@Entity
data class Artist(
    @PrimaryKey var name : String,
    var listeners: Long,
    var image: String
) {

    class TopArtistsJsonDeserializer : JsonDeserializer<List<Artist>> {
        companion object{
            private const val TOP_ARTISTS = "topartists"
            private const val ARTIST = "artist"
            private const val NAME = "name"
            private const val LISTENERS = "listeners"
            private const val IMAGE = "image"
            private const val SIZE = "size"
            private const val LARGE = "large"
            private const val TEXT = "#text"
        }
        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): List<Artist> {
            val topArtists = arrayListOf<Artist>()
            json?.also {
                val topArtistsJsonObject = it.asJsonObject.get(TOP_ARTISTS).asJsonObject
                val jsonArray = topArtistsJsonObject.get(ARTIST).asJsonArray
                jsonArray.forEach {
                    val artistJsonObject = it.asJsonObject
                    val name = artistJsonObject.get(NAME).asString
                    val listeners = artistJsonObject.get(LISTENERS).asLong
                    var avatarUrl = ""
                    artistJsonObject.get(IMAGE).asJsonArray.forEach {
                        if (it.asJsonObject.get(SIZE).asString == LARGE) {
                            avatarUrl = it.asJsonObject.get(TEXT).asString
                        }
                    }
                    topArtists.add(Artist(name, listeners, avatarUrl))
                }
            }
            return topArtists
        }
    }
}