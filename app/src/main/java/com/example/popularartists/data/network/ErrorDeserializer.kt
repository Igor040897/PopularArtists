package com.example.popularartists.data.network

import com.example.popularartists.data.models.ServerError
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

private const val ERROR = "error"
private const val ERROR_DESCRIPTION = "error_description"

class ErrorDeserializer: JsonDeserializer<ServerError> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ServerError? {
        json ?: return null
        val obj = json.asJsonObject
        var error = 500L
        try {
            error = obj.get(ERROR).asLong
        } catch(e: UnsupportedOperationException) {
            e.stackTrace
        }
        val description = obj.get(ERROR_DESCRIPTION).asString
        return ServerError(error, description)
    }
}