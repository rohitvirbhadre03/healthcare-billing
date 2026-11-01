package com.linx.billing.service

import com.linx.billing.exception.NotFoundException
import com.linx.billing.invoice.Bill
import com.linx.billing.invoice.BillCalculator
import com.linx.billing.repository.AppointmentRepository
import com.linx.billing.repository.DoctorRepository

class BillingService(
    private val appointmentRepository: AppointmentRepository,
    private val doctorRepository: DoctorRepository,
    private val billCalculator: BillCalculator,
) {

    fun generateBill(appointmentId: String): Bill {
        val appointment = appointmentRepository.findById(appointmentId)
            ?: throw NotFoundException("Appointment $appointmentId not found")
        val doctor = doctorRepository.findByNpi(appointment.doctorNpi)
            ?: throw NotFoundException("Doctor with NPI ${appointment.doctorNpi} not found")
        val priorCompletedVisits =
            appointmentRepository.countCompletedVisits(appointment.patientId, appointment.scheduledAt)

        return billCalculator.calculate(doctor, appointment.scheduledAt.toLocalDate(), priorCompletedVisits)
    }
}