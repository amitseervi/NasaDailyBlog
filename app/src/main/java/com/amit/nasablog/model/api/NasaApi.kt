package com.amit.nasablog.model.api

import com.amit.nasablog.model.entity.BlogDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {
    @GET("/planetary/apod")
    fun getPullRequest(
        @Query("api_key") apiKey: String,
        @Query("date") date: String? = null
    ): Call<BlogDetail>
}