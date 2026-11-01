package com.linx.billing.invoice

import com.linx.billing.tax.Gst
import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals

class BillCalculatorTest {

    private val calculator = BillCalculator(Gst());

    @Test
    fun `discount is applied before gst`() {
        val bill = calculator.calculate(consultationFee = BigDecimal("1500"), discountPercent = 3)

        assertEquals(
            Bill(
                consultationFee = BigDecimal("1500"),
                discountPercent = 3,
                discountAmount = BigDecimal("45.00"),
                taxableAmount = BigDecimal("1455.00"),
                gstAmount = BigDecimal("174.60"),
                totalAmount = BigDecimal("1629.60"),
            ),
            bill,
        )
    }

    @Test
    fun `max discount is 10 percent discount`() {
        val bill = calculator.calculate(consultationFee = BigDecimal("1500"), discountPercent = 10)
        assertEquals(BigDecimal("1512.00"), bill.totalAmount)
    }
}