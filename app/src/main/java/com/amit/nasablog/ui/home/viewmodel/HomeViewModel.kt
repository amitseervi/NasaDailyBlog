package com.amit.nasablog.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.amit.nasablog.model.entity.BlogDetail
import com.amit.nasablog.model.repository.NasaBlogRepository
import com.amit.nasablog.model.repository.NetworkStatus
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: NasaBlogRepository
) : ViewModel() {
    private val liveBlogData = repository.blogData
    var currentSelectedDate: Long = 0
        private set

    fun loadBlog() {
        repository.loadBlog()
    }

    fun loadBlogForDate(date: Date) {

        repository.loadBlog()
    }

    fun getBlogDetail(): LiveData<BlogDetail> {
        return liveBlogData.liveBlogDetail
    }

    fun getError(): LiveData<Throwable> {
        return liveBlogData.errorData
    }

    fun showProgress(): LiveData<Boolean> {
        return Transformations.map(liveBlogData.networkStatus) {
            it == NetworkStatus.IN_PROGRESS
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.dispose()
    }

    fun onDateClicked(it: Long?) {
        it ?: return
        currentSelectedDate = it
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val formatedDate = simpleDateFormat.format(Date(it))
        repository.loadBlog(formatedDate)
    }

}