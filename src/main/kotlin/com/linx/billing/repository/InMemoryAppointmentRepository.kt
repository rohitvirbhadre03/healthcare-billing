package com.linx.billing.repository

import com.linx.billing.model.Appointment
import com.linx.billing.model.AppointmentStatus
import java.time.LocalDateTime
import java.util.concurrent.ConcurrentHashMap

class InMemoryAppointmentRepository : AppointmentRepository {

    private val appointments = ConcurrentHashMap<String, Appointment>()

    override fun save(appointment: Appointment): Appointment {
        appointments[appointment.id] = appointment
        return appointment
    }

    override fun findById(id: String): Appointment? = appointments[id]

    override fun countCompletedVisits(patientId: String, time: LocalDateTime): Int =
        appointments.values.count {
            it.patientId == patientId &&
                    it.status == AppointmentStatus.COMPLETE &&
                    it.scheduledAt.isBefore(time)
        }
}