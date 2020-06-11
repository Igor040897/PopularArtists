package com.example.popularartists.data.preferences

import android.content.SharedPreferences


const val IS_NETWORK_DATA_MODE = "IS_NETWORK_DATA_MODE"

class SharedStorageManager private constructor(private val preferences: SharedPreferences) {
    fun saveDataMode(isNetwork: Boolean) {
        preferences.edit().putBoolean(IS_NETWORK_DATA_MODE, isNetwork).apply()
    }

    fun isNetworkDataMode() = preferences.getBoolean(IS_NETWORK_DATA_MODE, false)

    companion object {
        @Volatile
        private var INSTANCE: SharedStorageManager? = null

        fun getInstance(sharedPreferences: SharedPreferences): SharedStorageManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: SharedStorageManager(sharedPreferences).also { INSTANCE = it }
            }
        }
    }
}