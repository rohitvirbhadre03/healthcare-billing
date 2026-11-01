package com.linx.billing.model

import java.time.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class AppointmentTest {

    private val appointment = Appointment(
        id = "A1",
        patientId = "P1",
        doctorNpi = "1234567890",
        scheduledAt = LocalDateTime.of(2026, 10, 15, 10, 30),
    )

    @Test
    fun `new appointment is scheduled`() {
        assertEquals(AppointmentStatus.SCHEDULED, appointment.status)
    }

    @Test
    fun `complete appointment`() {
        assertEquals(AppointmentStatus.COMPLETE, appointment.complete().status)
    }

    @Test
    fun `cancel appointment`() {
        assertEquals(AppointmentStatus.CANCELLED, appointment.cancel().status)
    }
}