package com.linx.billing.service

import com.linx.billing.model.Doctor
import com.linx.billing.pricing.Speciality
import com.linx.billing.repository.InMemoryDoctorRepository
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DoctorServiceTest {

    private val repository = InMemoryDoctorRepository()
    private val service = DoctorService(repository)

    @Test
    fun `registered doctor`() {
        val doctor = doctorWithNpi("1234567890")
        service.register(doctor)
        assertEquals(doctor, repository.findByNpi("1234567890"))
    }

    @Test
    fun `duplicate NPI is rejected`() {
        val first = doctorWithNpi("1234567890")
        service.register(first)

        assertFailsWith<IllegalStateException> {
            service.register(first.copy(firstName = "Ravi"))
        }
        assertEquals(first, repository.findByNpi("1234567890"))
    }

    private fun doctorWithNpi(npi: String) =
        Doctor("Sagar", "B", npi, Speciality.CARDIO, LocalDate.of(2000, 6, 15))
}