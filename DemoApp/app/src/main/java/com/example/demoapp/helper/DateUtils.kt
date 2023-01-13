package com.example.demoapp.helper

import java.text.SimpleDateFormat
import java.util.*

fun getFormattedDate(matchDay: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("dd-MM-yyyy")
    val dateObj = inputFormat.parse(matchDay)
    return outputFormat.format(dateObj)
}
