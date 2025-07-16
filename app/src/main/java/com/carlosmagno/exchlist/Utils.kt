package com.carlosmagno.exchlist

fun formatLargeNumber(number: Double?): String {
    if (number == null) return "N/A"

    val absNumber = kotlin.math.abs(number)
    return when {
        absNumber >= 1_000_000_000 -> String.format("%.2fB", number / 1_000_000_000)
        absNumber >= 1_000_000 -> String.format("%.2fM", number / 1_000_000)
        absNumber >= 1_000 -> String.format("%.2fK", number / 1_000)
        else -> String.format("%.2f", number)
    }
}