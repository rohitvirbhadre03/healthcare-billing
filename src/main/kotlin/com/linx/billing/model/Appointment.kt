package com.linx.billing.model

import java.time.LocalDateTime

data class Appointment(
    val id: String,
    val patientId: String,
    val doctorNpi: String,
    val scheduledAt: LocalDateTime,
    val status: AppointmentStatus = AppointmentStatus.SCHEDULED,
) {

    fun complete(): Appointment = changeStatus(AppointmentStatus.COMPLETE)

    fun cancel(): Appointment = changeStatus(AppointmentStatus.CANCELLED)

    private fun changeStatus(newStatus: AppointmentStatus): Appointment {
        check(status == AppointmentStatus.SCHEDULED) { "Appointment $id is already $status" }
        return copy(status = newStatus)
    }
}