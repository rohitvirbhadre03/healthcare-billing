package com.linx.billing.service

import com.linx.billing.exception.NotFoundException
import com.linx.billing.model.Appointment
import com.linx.billing.repository.AppointmentRepository
import com.linx.billing.repository.DoctorRepository
import com.linx.billing.repository.PatientRepository
import java.time.LocalDateTime
import java.util.UUID

class AppointmentService(
    private val appointmentRepository: AppointmentRepository,
    private val patientRepository: PatientRepository,
    private val doctorRepository: DoctorRepository,
) {

    fun book(patientId: String, npi: String, scheduledAt: LocalDateTime): Appointment {
        patientRepository.findById(patientId) ?: throw NotFoundException("Patient $patientId not found")
        doctorRepository.findByNpi(npi) ?: throw NotFoundException("Doctor with NPI $npi not found")
        val appointment = Appointment(UUID.randomUUID().toString(), patientId, npi, scheduledAt)
        return appointmentRepository.save(appointment)
    }

    fun complete(appointmentId: String): Appointment =
        appointmentRepository.save(findAppointment(appointmentId).complete())

    fun cancel(appointmentId: String): Appointment =
        appointmentRepository.save(findAppointment(appointmentId).cancel())

    private fun findAppointment(id: String): Appointment =
        appointmentRepository.findById(id) ?: throw NotFoundException("Appointment $id not found")
}