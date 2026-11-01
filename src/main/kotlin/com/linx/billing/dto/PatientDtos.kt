package com.linx.billing.dto

import com.linx.billing.model.InsuranceInfo
import com.linx.billing.model.Patient
import com.linx.billing.util.formatUsDate
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class InsuranceDto(
    val bin: String,
    val pcn: String,
    val memberId: String,
) {
    fun toDomain() = InsuranceInfo(bin, pcn, memberId)

    companion object {
        fun from(insurance: InsuranceInfo) = InsuranceDto(insurance.bin, insurance.pcn, insurance.memberId)
    }
}

@Serdeable
data class PatientRequest(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val insurance: InsuranceDto,
)

@Serdeable
data class PatientResponse(
    val id: String,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val age: Int,
    val insurance: InsuranceDto,
) {
    companion object {
        fun from(patient: Patient, age: Int) = PatientResponse(
            id = patient.id,
            firstName = patient.firstName,
            lastName = patient.lastName,
            dateOfBirth = formatUsDate(patient.dateOfBirth),
            age = age,
            insurance = InsuranceDto.from(patient.insurance),
        )
    }
}