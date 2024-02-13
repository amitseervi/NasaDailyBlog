package com.amit.nasablog.model.repository

sealed interface RepoStatus<T> {
    data class Success<T>(val data: T) : RepoStatus<T>
    data class Error<T>(val code: Int, val message: String) : RepoStatus<T>

    class InProgress<T> : RepoStatus<T>
}