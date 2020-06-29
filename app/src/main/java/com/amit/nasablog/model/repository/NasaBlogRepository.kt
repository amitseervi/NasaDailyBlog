package com.amit.nasablog.model.repository

import com.amit.nasablog.BuildConfig
import com.amit.nasablog.model.api.NasaApi
import com.amit.nasablog.model.entity.BlogDetail
import com.amit.nasablog.utils.AppExecutors
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class NasaBlogRepository @Inject constructor(
    private val api: NasaApi,
    private val executor: AppExecutors
) {
    private val compositeDisposable = CompositeDisposable()
    val blogData = BlogData()
    fun loadBlog(date: String? = null) {
        if (blogData.networkStatus.value == NetworkStatus.IN_PROGRESS) {
            compositeDisposable.dispose()
        }
        setNetworkStatus(NetworkStatus.IN_PROGRESS)
        val disposable = api.getBlogData(BuildConfig.NASA_API_KEY, date)
            .subscribeOn(executor.networkIOScheduler())
            .observeOn(executor.mainThreadScheduler())
            .subscribe({
                setNetworkStatus(NetworkStatus.IDLE)
                if (it.isSuccessful) {
                    setBlogData(it.body())
                } else {
                    setBlogData(null)
                    setError(Exception(it.message()))
                }
            }, {
                setNetworkStatus(NetworkStatus.IDLE)
                setError(it)
            })
        compositeDisposable.add(disposable)
    }

    fun setBlogData(data: BlogDetail?) {
        setError(null)
        blogData.liveBlogDetail.postValue(data)
    }

    fun setError(error: Throwable?) {
        blogData.errorData.postValue(error)
    }

    fun setNetworkStatus(status: NetworkStatus) {
        blogData.networkStatus.postValue(status)
    }

    fun getNetworkStatus(): NetworkStatus {
        return blogData.networkStatus.value ?: NetworkStatus.IDLE
    }

    fun dispose() {
        compositeDisposable.dispose()
    }
}

