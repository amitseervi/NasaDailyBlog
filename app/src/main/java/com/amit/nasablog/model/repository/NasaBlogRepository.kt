package com.amit.nasablog.model.repository

import com.amit.nasablog.model.api.NasaApi
import java.util.*
import javax.inject.Inject

class NasaBlogRepository @Inject constructor(
    private val api: NasaApi
) {
    fun loadBlog() {

    }

    fun loadBlogForDate(date: Date) {

    }
}