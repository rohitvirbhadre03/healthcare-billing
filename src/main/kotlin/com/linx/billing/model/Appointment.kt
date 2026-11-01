package com.linx.billing.model

import java.time.LocalDateTime

data class Appointment(
    val id: String,
    val patientId: String,
    val doctorNpi: String,
    val scheduledAt: LocalDateTime,
    val status: AppointmentStatus = AppointmentStatus.SCHEDULED,
) {

    fun complete(): Appointment = copy(status = AppointmentStatus.COMPLETE)

    fun cancel(): Appointment = copy(status = AppointmentStatus.CANCELLED)
}