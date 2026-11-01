package com.linx.billing.tax

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals

class GstTest {

    private val gst = Gst()

    @Test
    fun `gst is 12 percent`() {
        assertEquals(BigDecimal("174.60"), gst.calculate(BigDecimal("1455.00")))
    }
}