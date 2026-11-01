package com.linx.billing.model

import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class PatientTest {

    private val insurance = InsuranceInfo(bin = "1234546", pcn = "ABCDEFG", memberId = "R123456")

    @Test
    fun `calculate age from date of birth`() {
        val patient = patient(LocalDate.of(1990, 3, 15))
        assertEquals(35, patient.ageOn(LocalDate.of(2025, 3, 15)))
    }

    @Test
    fun `first name is required`() {
        assertFailsWith<IllegalArgumentException> {
            Patient(" ", "J", LocalDate.of(1990, 3, 15), insurance)
        }
    }

    @Test
    fun `last name is required`() {
        assertFailsWith<IllegalArgumentException> {
            Patient("P", "", LocalDate.of(1990, 3, 15), insurance)
        }
    }

    private fun patient(dateOfBirth: LocalDate) = Patient("Priya", "Sharma", dateOfBirth, insurance)
}