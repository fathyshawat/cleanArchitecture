package com.example.fawrytask.common

import java.util.*

class Utils {

    companion object {
        fun getTimeAfterFourHours(): Long {
            val now: Calendar = Calendar.getInstance()
            val tmp: Calendar = now.clone() as Calendar
            tmp.add(Calendar.HOUR, 4)
            return tmp.timeInMillis
        }

        fun getTimeNow(): Long {
            val now: Calendar = Calendar.getInstance()
            return now.timeInMillis
        }
    }

}