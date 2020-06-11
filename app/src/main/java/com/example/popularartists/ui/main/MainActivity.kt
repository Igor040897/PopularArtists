package com.example.popularartists.ui.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.popularartists.BuildConfig
import com.example.popularartists.R
import com.example.popularartists.data.network.ConnectivityState
import com.example.popularartists.databinding.ActivityMainBinding
import com.example.popularartists.ui.base.BaseActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val contentLayoutId = R.layout.activity_main

    @Inject
    lateinit var connectivityStatus: ConnectivityState

    @Inject
    lateinit var connectivityManager: ConnectivityManager

    @Inject
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        connectivityNetworkCallback()
    }

    override fun setupBinding(binding: ActivityMainBinding) {
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        NavigationUI.setupActionBarWithNavController(
            this,
            findNavController(R.id.nav_host_fragment),
            null
        )

        if (BuildConfig.FLAVOR == "dev") {
            viewModel.changeDataMode(connectivityStatus.isConnected())
            binding.networkModeSwitch.visibility = View.VISIBLE
            setupSwitcherListenerDataMode()
        }
    }

    private fun connectivityNetworkCallback() {
        // check network
        if (connectivityStatus.isConnected()) {
            binding.networkModeSwitch.isChecked = true
            binding.networkModeSwitch.text = getString(R.string.online_mode)
            networkConnected()
        } else {
            networkDisconnected()
        }

        // register callback
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            receiverForConnectivityAction()
        } else {
            defaultNetworkCallback()
        }
    }

    private fun receiverForConnectivityAction() {
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }
        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                if (connectivityStatus.isConnected()) {
                    networkConnected()
                } else {
                    networkDisconnected()
                }
            }
        }, intentFilter)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun defaultNetworkCallback() {
        connectivityManager.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onCapabilitiesChanged(
                network: Network,
                capabilities: NetworkCapabilities
            ) {
                val connected = capabilities.hasCapability(NET_CAPABILITY_INTERNET)
                if (connected) {
                    networkConnected()
                } else {
                    networkDisconnected()
                }
            }

            override fun onLost(network: Network) {
                networkDisconnected()
            }
        })
    }

    private fun networkConnected() {
        runOnUiThread {
            binding.networkModeSwitch.isEnabled = true
            binding.networkIndicatorImageView.setImageResource(R.drawable.ic_signal_wifi_24dp)
        }
    }

    private fun networkDisconnected() {
        runOnUiThread {
            binding.networkModeSwitch.isEnabled = false
            binding.networkModeSwitch.isChecked = false
            binding.networkModeSwitch.text = getString(R.string.offline_mode)
            binding.networkIndicatorImageView.setImageResource(R.drawable.ic_signal_wifi_off_24dp)
        }
    }

    private fun setupSwitcherListenerDataMode() {
        binding.networkModeSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.networkModeSwitch.text = getString(R.string.online_mode)
                viewModel.changeDataMode(true)
            } else {
                binding.networkModeSwitch.text = getString(R.string.offline_mode)
                viewModel.changeDataMode(false)
            }
        }
    }
}