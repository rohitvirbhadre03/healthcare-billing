package com.linx.billing.dto

import com.linx.billing.model.Appointment
import com.linx.billing.model.AppointmentStatus
import io.micronaut.serde.annotation.Serdeable
import java.time.LocalDateTime

@Serdeable
data class AppointmentRequest(
    val patientId: String,
    val doctorNpi: String,
    val scheduledAt: LocalDateTime,
)

@Serdeable
data class AppointmentResponse(
    val id: String,
    val patientId: String,
    val doctorNpi: String,
    val scheduledAt: LocalDateTime,
    val status: AppointmentStatus,
) {
    companion object {
        fun from(appointment: Appointment) = AppointmentResponse(
            id = appointment.id,
            patientId = appointment.patientId,
            doctorNpi = appointment.doctorNpi,
            scheduledAt = appointment.scheduledAt,
            status = appointment.status,
        )
    }
}