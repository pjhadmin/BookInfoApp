package com.patch.bookinfoapp.common.util

import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*


fun Double.roundTo2DecimalPlaces() =
    BigDecimal(this).setScale(1, BigDecimal.ROUND_HALF_UP).toDouble()

fun Date.formatToServerDateDefaults(): String{
    val sdf= SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(this)
}