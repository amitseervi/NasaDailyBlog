package com.amit.nasablog.ui.home

import android.os.Parcel
import android.os.Parcelable
import com.google.android.material.datepicker.CalendarConstraints
import java.util.*

class DayValidator() : CalendarConstraints.DateValidator {
    private val utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    private val current = Calendar.getInstance(TimeZone.getTimeZone("UTC"))

    constructor(parcel: Parcel) : this()

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        
    }


    override fun isValid(d: Long): Boolean {
        utc.timeInMillis = d

        val date = utc[Calendar.DATE]
        val month = utc[Calendar.MONTH]
        val year = utc[Calendar.YEAR]

        val currentDate = current[Calendar.DATE]
        val currentMonth = current[Calendar.MONTH]
        val currentYear = current[Calendar.YEAR]

        if (year < currentYear) {
            return true
        }
        if (year > currentYear) {
            return false
        }

        if (month < currentMonth) {
            return true
        }
        if (month > currentMonth && year == currentYear) {
            return false
        }

        if (date > currentDate && month == currentMonth && year == currentYear) {
            return false
        }

        return true
    }

    companion object CREATOR : Parcelable.Creator<DayValidator> {
        override fun createFromParcel(parcel: Parcel): DayValidator {
            return DayValidator(parcel)
        }

        override fun newArray(size: Int): Array<DayValidator?> {
            return arrayOfNulls(size)
        }
    }
}