package com.example.popularartists.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

@Entity(
    indices = [Index(value = ["artistName"])],
    foreignKeys = [ForeignKey(
        entity = Artist::class,
        parentColumns = ["name"],
        childColumns = ["artistName"],
        onDelete = ForeignKey.CASCADE
    )]
)
open class Album(
    @PrimaryKey
    var name: String = "",
    var playcount: Long = -1,
    var image: String = "",
    var artistName: String = ""
) {
    constructor() : this("", -1, "", "")

    class TopAlbumsArtistJsonDeserializer : JsonDeserializer<List<Album>> {
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): List<Album> {
            val albums = arrayListOf<Album>()
            json?.also {
                //todo move to const
                val topalbumsJsonObject = it.asJsonObject.get("topalbums").asJsonObject
                val jsonArray = topalbumsJsonObject.get("album").asJsonArray
                jsonArray.forEach {
                    val albumJsonObject = it.asJsonObject
                    val name = albumJsonObject.get("name").asString
                    val playcount = albumJsonObject.get("playcount").asLong
                    var albumUrl = ""
                    albumJsonObject.get("image").asJsonArray.forEach {
                        if (it.asJsonObject.get("size").asString == "large") {
                            albumUrl = it.asJsonObject.get("#text").asString
                        }
                    }
                    albums.add(Album(name, playcount, albumUrl))
                }
            }
            return albums
        }
    }
}