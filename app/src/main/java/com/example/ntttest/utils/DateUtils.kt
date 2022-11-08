package com.example.ntttest.utils

import android.text.format.DateUtils
import android.util.Log
import java.util.*
import java.util.Calendar.*


// The term week in this function means Monday - Sunday
fun Date.isWithinThisWeek(): Boolean {
    val currentDay = Calendar.getInstance().get(DAY_OF_WEEK)
    Log.wtf("YASKO", "date week time: $currentDay")

    if (currentDay == MONDAY) return DateUtils.isToday(this.time)

    val firstDayOfWeekTime = Calendar.getInstance().apply {
        if (currentDay == SUNDAY) add(DATE, -6)
        else add(DATE, (currentDay - 2) * -1)

        Log.wtf("YASKO", "date calculation time: ${this.get(DAY_OF_WEEK)}")
    }.timeInMillis


    Log.wtf("YASKO", "book time: $time")
    Log.wtf("YASKO", "week time: $firstDayOfWeekTime")
    return this.time >= firstDayOfWeekTime
}
