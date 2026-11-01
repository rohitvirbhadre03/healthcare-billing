package com.linx.billing.controller

import com.linx.billing.dto.InsuranceDto
import com.linx.billing.dto.PatientRequest
import com.linx.billing.repository.InMemoryPatientRepository
import com.linx.billing.service.PatientService
import java.time.Clock
import java.time.LocalDate
import java.time.ZoneOffset
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PatientControllerTest {

    private val today = LocalDate.of(2025, 6, 15)
    private val clock = Clock.fixed(today.atStartOfDay().toInstant(ZoneOffset.UTC), ZoneOffset.UTC)
    private val controller = PatientController(PatientService(InMemoryPatientRepository(), clock))

    private val insurance = InsuranceDto(bin = "610014", pcn = "MEDDADV", memberId = "M123456")

    @Test
    fun `registering a patient returns the saved patient with age`() {
        val response = controller.register(PatientRequest("Priya", "Sharma", "03/15/1990", insurance))

        assertTrue(response.id.isNotBlank())
        assertEquals("Priya", response.firstName)
        assertEquals("Sharma", response.lastName)
        assertEquals("03/15/1990", response.dateOfBirth)
        assertEquals(35, response.age)
        assertEquals(insurance, response.insurance)
    }
}