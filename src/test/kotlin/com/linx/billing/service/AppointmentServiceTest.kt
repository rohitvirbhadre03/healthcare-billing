package com.linx.billing.service

import com.linx.billing.model.AppointmentStatus
import com.linx.billing.model.Doctor
import com.linx.billing.model.InsuranceInfo
import com.linx.billing.model.Patient
import com.linx.billing.pricing.Speciality
import com.linx.billing.repository.InMemoryAppointmentRepository
import com.linx.billing.repository.InMemoryDoctorRepository
import com.linx.billing.repository.InMemoryPatientRepository
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class AppointmentServiceTest {

    private val appointments = InMemoryAppointmentRepository()
    private val patients = InMemoryPatientRepository()
    private val doctors = InMemoryDoctorRepository()
    private val service = AppointmentService(appointments, patients, doctors)

    private val visitTime = LocalDateTime.of(2025, 6, 15, 10, 30)

    @BeforeTest
    fun registerPatientAndDoctor() {
        val insurance = InsuranceInfo(bin = "610014", pcn = "MEDDADV", memberId = "M123456")
        patients.save(Patient("P1", "Priya", "Sharma", LocalDate.of(1990, 3, 15), insurance))
        doctors.save(Doctor("Sagar", "B", "1234567890", Speciality.CARDIO, LocalDate.of(2000, 6, 15)))
    }

    @Test
    fun `schedule appointment`() {
        val appointment = service.book("P1", "1234567890", visitTime)
        assertEquals(AppointmentStatus.SCHEDULED, appointment.status)
        assertEquals(appointment, appointments.findById(appointment.id))
    }

    @Test
    fun `completing an appointment`() {
        val booked = service.book("P1", "1234567890", visitTime)
        service.complete(booked.id)
        assertEquals(AppointmentStatus.COMPLETE, appointments.findById(booked.id)?.status)
    }

    @Test
    fun `cancel an appointment`() {
        val booked = service.book("P1", "1234567890", visitTime)
        service.cancel(booked.id)
        assertEquals(AppointmentStatus.CANCELLED, appointments.findById(booked.id)?.status)
    }

}