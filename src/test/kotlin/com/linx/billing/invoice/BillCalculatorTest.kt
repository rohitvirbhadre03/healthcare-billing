package com.linx.billing.invoice

import com.linx.billing.discount.Discount
import com.linx.billing.insurance.Insurance
import com.linx.billing.model.Doctor
import com.linx.billing.pricing.FeeStructure
import com.linx.billing.pricing.Speciality
import com.linx.billing.tax.Gst
import java.math.BigDecimal
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

class BillCalculatorTest {

    private val calculator = BillCalculator(FeeStructure(), Discount(), Gst(), Insurance());

    private val cardio = Doctor(
        firstName = "Pravin",
        lastName = "Golande",
        npi = "1234567890",
        speciality = Speciality.CARDIO,
        practiceStartDate = LocalDate.of(2000, 6, 15),
    )

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
                insuranceApprovedAmount = BigDecimal("1466.64"),
                copayAmount = BigDecimal("162.96"),
            ),
            bill,
        )
    }

    @Test
    fun `max discount is 10 percent discount`() {
        val bill = calculator.calculate(consultationFee = BigDecimal("1500"), discountPercent = 10)
        assertEquals(BigDecimal("1512.00"), bill.totalAmount)
    }

    @Test
    fun `patient pays 10 percent of the total bill`() {
        val bill = calculator.calculate(consultationFee = BigDecimal("1500"), discountPercent = 10)
        assertEquals(BigDecimal("151.20"), bill.copayAmount)
    }

    @Test
    fun `fee calculated from the doctors specialty and experience`() {
        val bill = calculator.calculate(cardio, LocalDate.of(2025, 6, 15), priorVisits = 0)
        assertEquals(BigDecimal("1500"), bill.consultationFee)
    }

    @Test
    fun `discount calculated from prior visits`() {
        val bill = calculator.calculate(cardio, LocalDate.of(2025, 6, 15), priorVisits = 14)
        assertEquals(10, bill.discountPercent)
    }

}