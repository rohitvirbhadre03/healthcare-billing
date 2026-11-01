package com.linx.billing.pricing
import java.math.BigDecimal

class FeeStructure {

    fun feeFor(speciality: Speciality, experience: Int): BigDecimal =
            BigDecimal("1000")

}