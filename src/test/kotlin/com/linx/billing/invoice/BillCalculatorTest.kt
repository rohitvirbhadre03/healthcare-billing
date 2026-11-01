package com.linx.billing.invoice

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals

class BillCalculatorTest {

    private val calculator = BillCalculator();

    @Test
    fun `discount is applied`() {
        val bill = calculator.calculate(consultationFee = BigDecimal("1500"), discountPercent = 3)

        assertEquals(
            Bill(
                consultationFee = BigDecimal("1500"),
                discountPercent = 3,
                discountAmount = BigDecimal("45.00"),
                taxableAmount = BigDecimal("1455.00"),
                totalAmount = BigDecimal("1629.60"),
            ),
            bill,
        )
    }
}