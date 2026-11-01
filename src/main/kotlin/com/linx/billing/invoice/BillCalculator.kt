package com.linx.billing.invoice

import com.linx.billing.discount.Discount
import com.linx.billing.insurance.Insurance
import com.linx.billing.model.Doctor
import com.linx.billing.pricing.FeeStructure
import com.linx.billing.tax.Gst
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate

class BillCalculator(
    private val feeStructure: FeeStructure,
    private val discount: Discount,
    private val gst: Gst,
    private val insurance: Insurance
) {

    fun calculate(doctor: Doctor, appointmentDate: LocalDate, priorVisits: Int): Bill {
        val consultationFee = feeStructure.feeFor(doctor.speciality, doctor.experience(appointmentDate))
        val discountPercent = discount.percentage(priorVisits)
        return calculate(consultationFee, discountPercent)
    }

    fun calculate(consultationFee: BigDecimal, discountPercent: Int): Bill {
        val discountAmount = consultationFee.multiply(BigDecimal(discountPercent))
            .divide(BigDecimal(100))
            .setScale(2, RoundingMode.HALF_UP)
        val taxableAmount = consultationFee - discountAmount
        val gstAmount = gst.calculate(taxableAmount)
        val totalAmount = taxableAmount + gstAmount
        val coverage = insurance.split(totalAmount)

        return Bill(
            consultationFee = consultationFee,
            discountPercent = discountPercent,
            discountAmount = discountAmount,
            taxableAmount = taxableAmount,
            gstAmount = gstAmount,
            totalAmount = totalAmount,
            insuranceApprovedAmount = coverage.approvedAmount,
            copayAmount = coverage.copayAmount,
        )
    }
}