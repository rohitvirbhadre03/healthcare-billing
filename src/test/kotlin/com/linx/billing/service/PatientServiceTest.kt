package com.linx.billing.service

import com.linx.billing.model.InsuranceInfo
import com.linx.billing.repository.InMemoryPatientRepository
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PatientServiceTest {

    private val repository = InMemoryPatientRepository()
    private val service = PatientService(repository)
    private val insurance = InsuranceInfo(bin = "610014", pcn = "MEDDADV", memberId = "M123456")

    @Test
    fun `registered patient is saved with a generated id`() {
        val patient = registerPatient()

        assertTrue(patient.id.isNotBlank())
        assertEquals(patient, repository.findById(patient.id))
    }

    @Test
    fun `age is counted up to today`() {
        val patient = registerPatient(dateOfBirth = LocalDate.of(1990, 3, 15))
        assertEquals(36, service.ageToday(patient))
    }

    private fun registerPatient(dateOfBirth: LocalDate = LocalDate.of(1990, 3, 15)) =
        service.register("Sagar", "B", dateOfBirth, insurance)
}