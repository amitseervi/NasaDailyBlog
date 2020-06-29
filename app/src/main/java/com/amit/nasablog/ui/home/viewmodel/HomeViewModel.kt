package com.amit.nasablog.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.amit.nasablog.model.entity.BlogDetail
import com.amit.nasablog.model.repository.NasaBlogRepository
import java.util.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: NasaBlogRepository
) : ViewModel() {
    private val liveBlogData = repository.blogData
    fun loadBlog() {
        repository.loadBlog()
    }

    fun loadBlogForDate(date: Date) {
        repository.loadBlog(date)
    }

    fun getBlogDetail(): LiveData<BlogDetail> {
        return liveBlogData.liveBlogDetail
    }

    fun getError(): LiveData<Throwable> {
        return liveBlogData.errorData
    }

    override fun onCleared() {
        super.onCleared()
        repository.dispose()
    }

}