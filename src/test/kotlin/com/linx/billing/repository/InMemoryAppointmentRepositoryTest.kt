package com.linx.billing.repository

import com.linx.billing.model.Appointment
import com.linx.billing.model.AppointmentStatus
import java.time.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class InMemoryAppointmentRepositoryTest {

    private val repository = InMemoryAppointmentRepository()

    @Test
    fun `find appointment by id`() {
        val appointment = appointment("A1", "P1", LocalDateTime.of(2026, 9, 15, 10, 30))
        repository.save(appointment)
        assertEquals(appointment, repository.findById("A1"))
    }

    @Test
    fun `unknown appointment returns null`() {
        assertNull(repository.findById("missing"))
    }

    private fun appointment(
        id: String,
        patientId: String,
        scheduledAt: LocalDateTime,
        status: AppointmentStatus = AppointmentStatus.SCHEDULED,
    ) = Appointment(id, patientId, "1234567890", scheduledAt, status)
}