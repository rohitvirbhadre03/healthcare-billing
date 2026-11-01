package com.linx.billing.tax

import java.math.BigDecimal
import java.math.RoundingMode

class Gst(private val percentage: Int = 12) {
    fun calculate(amount: BigDecimal): BigDecimal =
        amount.multiply(BigDecimal(percentage))
            .divide(BigDecimal(100))
            .setScale(2, RoundingMode.HALF_UP)
}