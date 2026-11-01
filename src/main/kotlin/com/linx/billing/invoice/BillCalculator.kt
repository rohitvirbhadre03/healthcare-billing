package com.linx.billing.invoice

import java.math.BigDecimal
import java.math.RoundingMode

class BillCalculator {
    fun calculate(consultationFee: BigDecimal, discountPercent: Int): Bill {
        val discountAmount = consultationFee.multiply(BigDecimal(discountPercent))
            .divide(BigDecimal(100))
            .setScale(2, RoundingMode.HALF_UP)
        val taxableAmount = consultationFee - discountAmount

        return Bill(
            consultationFee = consultationFee,
            discountPercent = discountPercent,
            discountAmount = discountAmount,
            taxableAmount = taxableAmount,
            totalAmount = taxableAmount,
        )
    }
}