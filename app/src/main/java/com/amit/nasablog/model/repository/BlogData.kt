package com.amit.nasablog.model.repository

import androidx.lifecycle.MutableLiveData
import com.amit.nasablog.model.entity.BlogDetail

class BlogData {
    val liveBlogDetail: MutableLiveData<BlogDetail> =
        MutableLiveData()
    val errorData: MutableLiveData<Throwable> =
        MutableLiveData()
    val networkStatus: MutableLiveData<NetworkStatus> =
        MutableLiveData()
}