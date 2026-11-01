package com.linx.billing.model

import java.time.LocalDate
import java.time.Period

data class Patient(
    val id: String,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate,
    val insurance: InsuranceInfo,
) {
    init {
        require(firstName.isNotBlank()) { "First name is required" }
        require(lastName.isNotBlank()) { "Last name is required" }
    }
    fun ageOn(date: LocalDate): Int = Period.between(dateOfBirth, date).years
}

