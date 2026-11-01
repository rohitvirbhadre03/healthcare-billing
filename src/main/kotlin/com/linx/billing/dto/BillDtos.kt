package com.linx.billing.dto

import com.linx.billing.invoice.Bill
import io.micronaut.serde.annotation.Serdeable
import java.math.BigDecimal

@Serdeable
data class BillResponse(
    val consultationFee: BigDecimal,
    val discountPercent: Int,
    val discountAmount: BigDecimal,
    val taxableAmount: BigDecimal,
    val gstAmount: BigDecimal,
    val totalAmount: BigDecimal,
    val insuranceApprovedAmount: BigDecimal,
    val copayAmount: BigDecimal,
) {
    companion object {
        fun from(bill: Bill) = BillResponse(
            consultationFee = bill.consultationFee,
            discountPercent = bill.discountPercent,
            discountAmount = bill.discountAmount,
            taxableAmount = bill.taxableAmount,
            gstAmount = bill.gstAmount,
            totalAmount = bill.totalAmount,
            insuranceApprovedAmount = bill.insuranceApprovedAmount,
            copayAmount = bill.copayAmount,
        )
    }
}