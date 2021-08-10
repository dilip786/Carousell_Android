package com.android.carousell.utils

import java.util.*

fun Calendar.getTimeInText():String{
    val diffTime = Calendar.getInstance().timeInMillis - this.timeInMillis*1000
    val seconds = diffTime / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    val years = days/365
    return when{
        years >1 -> "$years years ago"
        days >1 -> "$days days ago"
        hours >1 -> "$hours hours ago"
        minutes >1 -> "$minutes minutes ago"
        else ->{
            "$seconds seconds ago"
        }
    }
}