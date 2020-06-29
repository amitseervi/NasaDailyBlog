package com.amit.nasablog.model.cache.interceptor

import com.amit.nasablog.BuildConfig
import com.amit.nasablog.NasaBlogApp
import okhttp3.Interceptor
import okhttp3.Response

class NetworkCacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val networkAvailable = NasaBlogApp.networkAvailable.value == true
        val newRequest = if (networkAvailable) {
            request.newBuilder().header("Cache-Control", "public, max-age=" + BuildConfig.MAX_AGE).build()
        } else {
            request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=" + BuildConfig.MAX_STALE)
                .build()
        }
        return chain.proceed(newRequest)
    }

}