package com.amit.nasablog

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


@HiltAndroidApp
class NasaBlogApp : Application() {
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            networkAvailable.postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            networkAvailable.postValue(false)

        }
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            val tree = Timber.DebugTree()
            Timber.plant(tree)
        }
        registerNetworkCallback()
    }

    companion object {
        val networkAvailable: MutableLiveData<Boolean> = MutableLiveData(true)
    }

    private fun registerNetworkCallback() {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest =
            NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()
        cm.registerNetworkCallback(networkRequest, networkCallback)
    }
}