package com.amit.nasablog.model.repository

import com.amit.nasablog.BuildConfig
import com.amit.nasablog.model.api.NasaApi
import com.amit.nasablog.model.entity.BlogDetail
import com.amit.nasablog.model.repository.RepoStatus.InProgress
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class NasaBlogRepository @Inject constructor(
    private val api: NasaApi,
) {

    private val _repoStatus: MutableStateFlow<RepoStatus<BlogDetail>> =
        MutableStateFlow(InProgress())
    val status: StateFlow<RepoStatus<BlogDetail>>
        get() = _repoStatus

    suspend fun loadBlog(date: String? = null) {
        if (_repoStatus.value is InProgress) {
            return
        }
        _repoStatus.emit(InProgress())
        val response = api.getBlogData(BuildConfig.NASA_API_KEY, date)
        if (response.isSuccessful) {
            response.body().let { blogDetail ->
                if (blogDetail != null) {
                    _repoStatus.emit(RepoStatus.Success(blogDetail))
                } else {
                    _repoStatus.emit(RepoStatus.Error(4000, "Null blog value returned from api"))
                }
            }

        } else {
            _repoStatus.emit(RepoStatus.Error(response.code(), response.message()))
        }
    }
}

