package com.linx.billing.repository

import com.linx.billing.model.InsuranceInfo
import com.linx.billing.model.Patient
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

class InMemoryPatientRepositoryTest {

    private val repository = InMemoryPatientRepository()

    @Test
    fun `find patient by id`() {
        val patient = Patient(
            id = "P1",
            firstName = "Priya",
            lastName = "Sharma",
            dateOfBirth = LocalDate.of(1990, 3, 15),
            insurance = InsuranceInfo(bin = "610014", pcn = "MMAAMNN", memberId = "M123456"),
        )
        repository.save(patient)
        assertEquals(patient, repository.findById("P1"))
    }
}