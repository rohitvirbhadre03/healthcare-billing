package com.linx.billing.repository

import com.linx.billing.model.Appointment
import com.linx.billing.model.AppointmentStatus
import java.time.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class InMemoryAppointmentRepositoryTest {

    private val repository = InMemoryAppointmentRepository()

    private val visitTime = LocalDateTime.of(2025, 6, 15, 10, 30)

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

    @Test
    fun `counts completed visits of the patient`() {
        repository.save(appointment("A1", "P1", LocalDateTime.of(2025, 1, 10, 9, 0), AppointmentStatus.COMPLETE))
        repository.save(appointment("A2", "P1", LocalDateTime.of(2025, 3, 5, 9, 0), AppointmentStatus.COMPLETE))
        assertEquals(2, repository.countCompletedVisits("P1", visitTime))
    }

    private fun appointment(
        id: String,
        patientId: String,
        scheduledAt: LocalDateTime,
        status: AppointmentStatus = AppointmentStatus.SCHEDULED,
    ) = Appointment(id, patientId, "1234567890", scheduledAt, status)
}