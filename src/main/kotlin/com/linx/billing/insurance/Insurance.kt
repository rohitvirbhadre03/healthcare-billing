package com.linx.billing.insurance

import java.math.BigDecimal
import java.math.RoundingMode

class Insurance(private val insurancePercent: Int = 90) {
    fun split(total: BigDecimal): Coverage {
        val approvedAmount = total.multiply(BigDecimal(insurancePercent))
            .divide(BigDecimal(100))
            .setScale(2, RoundingMode.HALF_UP)
        return Coverage(
            approvedAmount = approvedAmount,
            copayAmount = total - approvedAmount,
        )
    }
}