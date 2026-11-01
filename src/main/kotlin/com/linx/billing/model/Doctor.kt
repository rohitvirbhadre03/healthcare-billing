package com.linx.billing.model

import com.linx.billing.pricing.Speciality
import java.time.LocalDate
import java.time.Period

data class Doctor(
    val firstName: String,
    val lastName: String,
    val npi: String,
    val speciality: Speciality,
    val practiceStartDate: LocalDate
) {
    fun experience(date: LocalDate): Int = Period.between(practiceStartDate, date).years
}
