package com.example.popularartists.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index(value = ["albumName"])],
    foreignKeys = [ForeignKey(
        entity = Album::class,
        parentColumns = ["name"],
        childColumns = ["albumName"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Track(
    @PrimaryKey
    val name: String,
    val duration: Long,
    var albumName: String = ""
)