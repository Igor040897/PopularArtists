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
        companion object{
            private const val TOP_ALBUMS = "topalbums"
            private const val ALBUM = "album"
            private const val NAME = "name"
            private const val PLAY_COUNT = "playcount"
            private const val IMAGE = "image"
            private const val SIZE = "size"
            private const val LARGE = "large"
            private const val TEXT = "#text"
        }
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): List<Album> {
            val albums = arrayListOf<Album>()
            json?.also {
                val topalbumsJsonObject = it.asJsonObject.get(TOP_ALBUMS).asJsonObject
                val jsonArray = topalbumsJsonObject.get(ALBUM).asJsonArray
                jsonArray.forEach {
                    val albumJsonObject = it.asJsonObject
                    val name = albumJsonObject.get(NAME).asString
                    val playcount = albumJsonObject.get(PLAY_COUNT).asLong
                    var albumUrl = ""
                    albumJsonObject.get(IMAGE).asJsonArray.forEach {
                        if (it.asJsonObject.get(SIZE).asString == LARGE) {
                            albumUrl = it.asJsonObject.get(TEXT).asString
                        }
                    }
                    albums.add(Album(name, playcount, albumUrl))
                }
            }
            return albums
        }
    }
}