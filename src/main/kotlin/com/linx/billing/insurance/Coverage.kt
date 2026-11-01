package com.linx.billing.insurance

import java.math.BigDecimal

data class Coverage(
    val approvedAmount: BigDecimal,
    val copayAmount: BigDecimal,
)
