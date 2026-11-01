package com.linx.billing.model

import com.linx.billing.pricing.Speciality
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DoctorTest {

    @Test
    fun `calculate experience from practice start`() {
        val doctor = doctorStartedOn(LocalDate.of(2001, 6, 15))
        assertEquals(25, doctor.experience(LocalDate.of(2026, 6, 15)))
    }

    @Test
    fun `first name is required`() {
        assertFailsWith<IllegalArgumentException> {
            Doctor(" ", "A", "1234567890", Speciality.CARDIO, LocalDate.of(2000, 6, 15))
        }
    }

    @Test
    fun `last name is required`() {
        assertFailsWith<IllegalArgumentException> {
            Doctor("A", "", "1234567890", Speciality.CARDIO, LocalDate.of(2000, 6, 15))
        }
    }

    @Test
    fun `npi is required`() {
        assertFailsWith<IllegalArgumentException> {
            Doctor(" ", "A", " ", Speciality.CARDIO, LocalDate.of(2000, 6, 15))
        }
    }

    private fun doctorStartedOn(practiceStartDate: LocalDate) =
        Doctor(
            firstName = "Praveen",
            lastName = "Golande",
            npi = "1234567890",
            speciality = Speciality.CARDIO,
            practiceStartDate = practiceStartDate,
        )
}