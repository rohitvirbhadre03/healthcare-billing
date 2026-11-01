package com.linx.billing.dto

import com.linx.billing.model.Doctor
import com.linx.billing.pricing.Speciality
import com.linx.billing.util.formatUsDate
import com.linx.billing.util.parseUsDate
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class DoctorRequest(
    val firstName: String,
    val lastName: String,
    val npi: String,
    val speciality: Speciality,
    val practiceStartDate: String,
) {
    fun toDomain() = Doctor(firstName, lastName, npi, speciality, parseUsDate(practiceStartDate))
}

@Serdeable
data class DoctorResponse(
    val firstName: String,
    val lastName: String,
    val npi: String,
    val speciality: Speciality,
    val practiceStartDate: String,
) {
    companion object {
        fun from(doctor: Doctor) = DoctorResponse(
            firstName = doctor.firstName,
            lastName = doctor.lastName,
            npi = doctor.npi,
            speciality = doctor.speciality,
            practiceStartDate = formatUsDate(doctor.practiceStartDate),
        )
    }
}