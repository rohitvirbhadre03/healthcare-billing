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

    init {
        require(firstName.isNotBlank()) { "First name is required" }
        require(lastName.isNotBlank()) { "Last name is required" }
        require(npi.isNotBlank()) { "NPI is required" }
    }

    fun experience(date: LocalDate): Int = Period.between(practiceStartDate, date).years
}
