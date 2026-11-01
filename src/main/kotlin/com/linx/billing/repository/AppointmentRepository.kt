package com.linx.billing.repository

import com.linx.billing.model.Appointment

interface AppointmentRepository {

    fun save(appointment: Appointment): Appointment

    fun findById(id: String): Appointment?
}