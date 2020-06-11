package com.example.popularartists.data.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.net.ConnectivityManager
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class NetworkStateChangeReceiver : BroadcastReceiver() {
    val NETWORK_AVAILABLE_ACTION = "NETWORK_AVAILABLE_ACTION"
    val IS_NETWORK_AVAILABLE = "isNetworkAvailable"

    override fun onReceive(context: Context?, intent: Intent?) {
        val networkStateIntent = Intent(NETWORK_AVAILABLE_ACTION)
        networkStateIntent.putExtra(IS_NETWORK_AVAILABLE, isConnectedToInternet(context))
        context?.run { LocalBroadcastManager.getInstance(this).sendBroadcast(networkStateIntent) }
    }

    private fun isConnectedToInternet(context: Context?): Boolean {
        return try {
            if (context != null) {
                val connectivityManager =
                    context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
                val networkInfo = connectivityManager.activeNetworkInfo
                return networkInfo != null && networkInfo.isConnected
            }
            false
        } catch (e: Exception) {
//            Log.e(NetworkStateChangeReceiver::class.java.name, e.message)
            false
        }
    }
}