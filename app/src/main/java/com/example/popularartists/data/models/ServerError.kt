package com.example.popularartists.data.models

import com.google.gson.annotations.SerializedName

data class ServerError(@SerializedName("error") val error: Long,
                       @SerializedName("message") val message: String)