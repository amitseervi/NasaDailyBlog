package com.amit.nasablog.utils

import java.text.DateFormat
import java.util.*

class Utils {
    companion object {
        @JvmStatic
        fun formatDate(prefix: String, date: Date?): String? {
            date ?: return null
            val dateFormat = DateFormat.getInstance()
            return prefix + dateFormat.format(date)
        }
    }
}