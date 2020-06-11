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
        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): List<Artist> {
            val topArtists = arrayListOf<Artist>()
            json?.also {
                //todo move to const
                val topartistsJsonObject = it.asJsonObject.get("topartists").asJsonObject
                val jsonArray = topartistsJsonObject.get("artist").asJsonArray
                jsonArray.forEach {
                    val artistJsonObject = it.asJsonObject
                    val name = artistJsonObject.get("name").asString
                    val listeners = artistJsonObject.get("listeners").asLong
                    val avatarUrl =
                        artistJsonObject.get("image").asJsonArray.first().asJsonObject.get("#text").asString
                    topArtists.add(Artist(name, listeners, avatarUrl))
                }
            }
            return topArtists
        }
    }
}