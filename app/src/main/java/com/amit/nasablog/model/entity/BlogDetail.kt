package com.amit.nasablog.model.entity

import com.google.gson.annotations.SerializedName
import java.util.*

data class BlogDetail(
    @SerializedName("copyright") val copyright: String?,
    @SerializedName("date") val date: Date?,
    @SerializedName("explanation") val explanation: String?,
    @SerializedName("hdurl") val hdurl: String?,
    @SerializedName("media_type") val mediaType: String?,
    @SerializedName("service_version") val serviceVersion: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("url") val url: String?
)