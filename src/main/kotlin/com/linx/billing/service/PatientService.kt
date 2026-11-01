package com.linx.billing.service

import com.linx.billing.model.InsuranceInfo
import com.linx.billing.model.Patient
import com.linx.billing.repository.PatientRepository
import java.time.LocalDate
import java.util.UUID

class PatientService(private val patientRepository: PatientRepository) {

    fun register(
        firstName: String,
        lastName: String,
        dateOfBirth: LocalDate,
        insurance: InsuranceInfo,
    ): Patient {
        val patient = Patient(UUID.randomUUID().toString(), firstName, lastName, dateOfBirth, insurance)
        return patientRepository.save(patient)
    }
}