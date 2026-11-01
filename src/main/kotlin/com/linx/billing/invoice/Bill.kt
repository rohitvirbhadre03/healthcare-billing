package com.linx.billing.invoice

import java.math.BigDecimal

data class Bill(
    val consultationFee: BigDecimal,
    val discountPercent: Int,
    val discountAmount: BigDecimal,
    val taxableAmount: BigDecimal,
    val gstAmount: BigDecimal,
    val totalAmount: BigDecimal,
)