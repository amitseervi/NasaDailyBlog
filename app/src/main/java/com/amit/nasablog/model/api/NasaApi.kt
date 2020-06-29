package com.amit.nasablog.model.api

import com.amit.nasablog.model.entity.BlogDetail
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {
    @GET("/planetary/apod")
    fun getBlogData(
        @Query("api_key") apiKey: String,
        @Query("date") date: String?=null
    ): Single<Response<BlogDetail>>
}