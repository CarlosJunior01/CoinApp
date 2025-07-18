package com.carlosmagno.exchlist

import java.util.Locale

fun formatLargeNumber(number: Double?): String {
    if (number == null) return "N/A"

    val absNumber = kotlin.math.abs(number)
    return when {
        absNumber >= 1_000_000_000 -> String.format(Locale.US, "%.2fB", number / 1_000_000_000)
        absNumber >= 1_000_000 -> String.format(Locale.US, "%.2fM", number / 1_000_000)
        absNumber >= 1_000 -> String.format(Locale.US, "%.2fK", number / 1_000)
        else -> String.format(Locale.US, "%.2f", number)
    }
}