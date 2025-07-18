package com.carlosmagno.exchlist

import org.junit.Assert.assertEquals
import org.junit.Test

class FormatLargeNumberTest {

    @Test
    fun `returns N_A for null input`() {
        assertEquals("N/A", formatLargeNumber(null))
    }

    @Test
    fun `formats billions correctly`() {
        assertEquals("1.23B", formatLargeNumber(1_230_000_000.0))
        assertEquals("-2.50B", formatLargeNumber(-2_500_000_000.0))
    }

    @Test
    fun `formats millions correctly`() {
        assertEquals("3.45M", formatLargeNumber(3_450_000.0))
        assertEquals("-4.00M", formatLargeNumber(-4_000_000.0))
    }

    @Test
    fun `formats thousands correctly`() {
        assertEquals("5.67K", formatLargeNumber(5_670.0))
        assertEquals("-6.00K", formatLargeNumber(-6_000.0))
    }

    @Test
    fun `formats small numbers with two decimals`() {
        assertEquals("123.45", formatLargeNumber(123.45))
        assertEquals("-99.99", formatLargeNumber(-99.99))
        assertEquals("0.00", formatLargeNumber(0.0))
    }
}
