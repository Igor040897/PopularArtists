package com.example.popularartists.data.network

import android.net.ConnectivityManager

class ConnectivityDispatcher private constructor(private val connectivityManager: ConnectivityManager) :
    ConnectivityState {

    companion object {

        @Volatile
        private var INSTANCE: ConnectivityDispatcher? = null

        fun getInstance(connectivityManager: ConnectivityManager): ConnectivityDispatcher =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: init(connectivityManager).also { INSTANCE = it }
            }

        private fun init(connectivityManager: ConnectivityManager) =
            ConnectivityDispatcher(connectivityManager)
    }

    override fun isConnected(): Boolean {
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}