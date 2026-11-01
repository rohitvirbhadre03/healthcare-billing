package com.linx.billing.pricing

import java.math.BigDecimal

class FeeStructure {

    fun feeFor(speciality: Speciality, experience: Int): BigDecimal =
        when (speciality) {
            Speciality.ORTHO -> when {
                experience < 20 -> BigDecimal("800")
                experience < 30 -> BigDecimal("1000")
                else -> BigDecimal("1500")
            }

            Speciality.CARDIO -> when {
                experience < 20 -> BigDecimal("1000")
                experience < 30 -> BigDecimal("1500")
                else -> BigDecimal("2000")
            }
        }
}