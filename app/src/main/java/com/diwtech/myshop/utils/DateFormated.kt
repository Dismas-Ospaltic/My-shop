package com.diwtech.myshop.utils

import android.icu.text.SimpleDateFormat
import java.util.*

fun dateFormated(timestamp: Long): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(Date(timestamp))
}