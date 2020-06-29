package com.amit.nasablog.model.entity

import com.google.gson.annotations.SerializedName
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

data class BlogDetail(
    @SerializedName("copyright") val copyright: String?,
    @SerializedName("date") val date: Date?,
    @SerializedName("explanation") val explanation: String?,
    @SerializedName("hdurl") val hdurl: String?,
    @SerializedName("media_type") val mediaType: String?,
    @SerializedName("service_version") val serviceVersion: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("url") val url: String?
) {
    fun isVideoSource(): Boolean {
        return mediaType == "video"
    }

    fun isYoutubeVideo(): Boolean {
        url ?: return false
        return getYoutubeId() != null
    }

    fun getYoutubeId(): String? {
        url ?: return null
        var vId: String? = null
        val pattern: Pattern = Pattern.compile(
            youtubeRegex,
            Pattern.CASE_INSENSITIVE
        )
        val matcher: Matcher = pattern.matcher(url)
        if (matcher.matches()) {
            vId = matcher.group(1)
        }
        return vId
    }

    fun getThumbnail(): String? {
        if (mediaType == mediaTypeVideo) {
            val youTubeId = getYoutubeId() ?: return null
            return getYoutubeThumbnailUrl(youTubeId)
        } else {
            return url
        }
    }

    private fun getYoutubeThumbnailUrl(id: String): String {
        return String.format(youtubeThumbnail, id)
    }

    companion object {
        const val youtubeRegex = "^https?://.*(?:youtu.be/|v/|u/\\w/|embed/|watch?v=)([^#&?]*).*\$"
        const val youtubeThumbnail = "https://img.youtube.com/vi/%s/hqdefault.jpg"
        const val mediaTypeVideo = "video"
    }
}