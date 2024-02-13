package com.amit.nasablog.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amit.nasablog.model.entity.BlogDetail
import com.amit.nasablog.model.repository.NasaBlogRepository
import com.amit.nasablog.model.repository.RepoStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NasaBlogRepository
) : ViewModel() {
    var currentSelectedDate: Long = 0
        private set

    private val _currentBlogDetail: MutableStateFlow<BlogDetail?> = MutableStateFlow(null)

    val blogDetail: StateFlow<BlogDetail?>
        get() = _currentBlogDetail

    private val _errorResponse: MutableStateFlow<Throwable?> = MutableStateFlow(null)

    val errorResponse: StateFlow<Throwable?>
        get() = _errorResponse

    private val _showProgress: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val showProgress: StateFlow<Boolean>
        get() = _showProgress

    init {
        viewModelScope.launch {
            repository.status.collectLatest {
                if (it is RepoStatus.Success) {
                    _currentBlogDetail.emit(it.data)
                    _errorResponse.emit(null)
                    _showProgress.emit(false)
                } else if (it is RepoStatus.Error) {
                    _errorResponse.emit(RuntimeException(it.message))
                    _showProgress.emit(false)
                } else {
                    _errorResponse.emit(null)
                    _showProgress.emit(true)
                }
            }
        }
    }

    fun loadBlog() {
        viewModelScope.launch {
            repository.loadBlog()
        }
    }


    fun onDateClicked(it: Long?) {
        it ?: return
        currentSelectedDate = it
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val formattedDate = simpleDateFormat.format(Date(it))
        viewModelScope.launch {
            repository.loadBlog(formattedDate)
        }
    }

}