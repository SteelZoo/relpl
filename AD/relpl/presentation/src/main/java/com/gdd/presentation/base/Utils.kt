package com.gdd.presentation.base

import android.content.Intent
import android.os.Build
import java.io.Serializable
import java.text.DecimalFormat

// API 33 이후 getSerializable() deprecated 대응
/**
 * intent.intentSerializable("data", T::class.java)
 * 위와 같이 사용
 */
fun <T: Serializable> Intent.intentSerializable(key: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getSerializableExtra(key, clazz)
    } else {
        this.getSerializableExtra(key) as T?
    }
}

fun Int.pointFormat(): String{
    val dec = DecimalFormat("#,###")
    return "${dec.format(this)} P"
}

fun Int.distanceFormat(): String{
    val km = if (this / 1000 < 1) "" else "${(this/1000)}km"
    val m = this % 1000

    return "$km ${m}m"
}
