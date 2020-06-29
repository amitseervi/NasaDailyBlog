package com.amit.nasablog.di.modules

import android.app.Application
import com.amit.nasablog.BuildConfig
import com.amit.nasablog.NasaBlogApp
import com.amit.nasablog.di.scopes.NetworkExecutor
import com.amit.nasablog.model.api.NasaApi
import com.amit.nasablog.model.cache.interceptor.NetworkCacheInterceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton


@Module
abstract class AppModule {
    @Binds
    abstract fun application(application: NasaBlogApp): Application

    companion object {
        @JvmStatic
        @Singleton
        @Provides
        fun provideNasaBlogApi(okHttpClient: OkHttpClient, gson: Gson): NasaApi {
            val retrofit = Retrofit.Builder().client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.NASA_BASE_URL)
                .build()

            return retrofit.create(NasaApi::class.java)
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideGson(): Gson {
            return GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create()
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideOkHTTPClient(context: Application): OkHttpClient {
            val okHttpClient = OkHttpClient.Builder()
            okHttpClient.cache(Cache(context.cacheDir, BuildConfig.NETWORK_CACHE_SIZE))
            okHttpClient.addInterceptor(NetworkCacheInterceptor())
            okHttpClient.addNetworkInterceptor(StethoInterceptor())
            return okHttpClient.build()
        }

        @JvmStatic
        @Singleton
        @Provides
        @NetworkExecutor
        fun provideNetworkExecutor(): Executor {
            return Executors.newCachedThreadPool()
        }
    }
}