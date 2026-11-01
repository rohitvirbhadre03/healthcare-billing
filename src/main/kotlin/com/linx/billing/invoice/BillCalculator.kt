package com.linx.billing.invoice

import com.linx.billing.tax.Gst
import java.math.BigDecimal
import java.math.RoundingMode

class BillCalculator(val gst: Gst) {
    fun calculate(consultationFee: BigDecimal, discountPercent: Int): Bill {
        val discountAmount = consultationFee.multiply(BigDecimal(discountPercent))
            .divide(BigDecimal(100))
            .setScale(2, RoundingMode.HALF_UP)
        val taxableAmount = consultationFee - discountAmount
        val gstAmount = gst.calculate(taxableAmount)

        return Bill(
            consultationFee = consultationFee,
            discountPercent = discountPercent,
            discountAmount = discountAmount,
            taxableAmount = taxableAmount,
            gstAmount = gstAmount,
            totalAmount = taxableAmount + gstAmount
        )
    }
}