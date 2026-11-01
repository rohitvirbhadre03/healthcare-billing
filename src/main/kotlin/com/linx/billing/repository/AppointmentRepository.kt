package com.linx.billing.repository

import com.linx.billing.model.Appointment
import java.time.LocalDateTime

interface AppointmentRepository {

    fun save(appointment: Appointment): Appointment

    fun findById(id: String): Appointment?

    fun countCompletedVisits(patientId: String, time: LocalDateTime): Int
}