package com.amit.nasablog

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.MutableLiveData
import com.amit.nasablog.di.components.DaggerApplicationComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit


class NasaBlogApp : DaggerApplication() {
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        private var networkTimeout: Disposable? = null
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            networkTimeout?.dispose()
            Timber.d("Network Available")
            networkAvailable.postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            networkTimeout?.dispose()
            networkTimeout = Single.timer(5, TimeUnit.SECONDS).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe { remain ->
                    if (remain == 0L) {
                        Timber.d("Network Lost")
                        networkAvailable.postValue(false)
                    }
                }
        }
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.logEnabled) {
            Stetho.initializeWithDefaults(this)
            val tree = Timber.DebugTree()
            Timber.plant(tree)
        }
        registerNetworkCallback()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().application(this).build()
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