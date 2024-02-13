package com.amit.nasablog.di.modules

import android.app.Application
import com.amit.nasablog.BuildConfig
import com.amit.nasablog.model.api.NasaApi
import com.amit.nasablog.model.cache.interceptor.NetworkCacheInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideNasaBlogApi(okHttpClient: OkHttpClient, gson: Gson): NasaApi {
        val retrofit = Retrofit.Builder().client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BuildConfig.NASA_BASE_URL)
            .build()

        return retrofit.create(NasaApi::class.java)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create()
    }

    @Singleton
    @Provides
    fun provideOkHTTPClient(context: Application): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.cache(Cache(context.cacheDir, BuildConfig.NETWORK_CACHE_SIZE))
        okHttpClient.addNetworkInterceptor(NetworkCacheInterceptor())
        okHttpClient.addInterceptor(HttpLoggingInterceptor())
        return okHttpClient.build()
    }

}