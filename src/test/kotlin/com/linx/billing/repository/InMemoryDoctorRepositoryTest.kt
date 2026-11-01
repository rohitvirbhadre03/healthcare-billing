package com.linx.billing.repository

import com.linx.billing.model.Doctor
import com.linx.billing.pricing.Speciality
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

class InMemoryDoctorRepositoryTest {

    private val repository = InMemoryDoctorRepository()

    @Test
    fun `saved doctor can be found by NPI`() {
        val doctor = Doctor(
            firstName = "Asha",
            lastName = "Rao",
            npi = "1234567890",
            speciality = Speciality.CARDIO,
            practiceStartDate = LocalDate.of(2000, 6, 15),
        )

        repository.save(doctor)
        assertEquals(doctor, repository.findByNpi("1234567890"))
    }
}