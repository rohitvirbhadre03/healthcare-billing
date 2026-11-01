package com.linx.billing.controller

import com.linx.billing.dto.AppointmentRequest
import com.linx.billing.model.AppointmentStatus
import com.linx.billing.model.Doctor
import com.linx.billing.model.InsuranceInfo
import com.linx.billing.model.Patient
import com.linx.billing.pricing.Speciality
import com.linx.billing.repository.InMemoryAppointmentRepository
import com.linx.billing.repository.InMemoryDoctorRepository
import com.linx.billing.repository.InMemoryPatientRepository
import com.linx.billing.service.AppointmentService
import com.linx.billing.service.BillingService
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class AppointmentControllerTest {

    private val appointments = InMemoryAppointmentRepository()
    private val patients = InMemoryPatientRepository()
    private val doctors = InMemoryDoctorRepository()
    private val controller = AppointmentController(AppointmentService(appointments, patients, doctors),
        BillingService(appointments, doctors),)

    private val visitTime = LocalDateTime.of(2025, 6, 15, 10, 30)

    @BeforeTest
    fun registerPatientAndDoctor() {
        val insurance = InsuranceInfo(bin = "610014", pcn = "MEDDADV", memberId = "M123456")
        patients.save(Patient("P1", "Priya", "Sharma", LocalDate.of(1990, 3, 15), insurance))
        doctors.save(Doctor("Asha", "Rao", "1234567890", Speciality.CARDIO, LocalDate.of(2000, 6, 15)))
    }

    @Test
    fun `booking returns a scheduled appointment`() {
        val response = controller.book(AppointmentRequest("P1", "1234567890", visitTime))

        assertEquals("P1", response.patientId)
        assertEquals("1234567890", response.doctorNpi)
        assertEquals(visitTime, response.scheduledAt)
        assertEquals(AppointmentStatus.SCHEDULED, response.status)
    }

    @Test
    fun `completing returns the appointment as complete`() {
        val booked = controller.book(AppointmentRequest("P1", "1234567890", visitTime))
        val response = controller.complete(booked.id)
        assertEquals(AppointmentStatus.COMPLETE, response.status)
    }

    @Test
    fun `cancelling returns the appointment as cancelled`() {
        val booked = controller.book(AppointmentRequest("P1", "1234567890", visitTime))
        val response = controller.cancel(booked.id)
        assertEquals(AppointmentStatus.CANCELLED, response.status)
    }

    @Test
    fun `bill includes the discount for visits`() {
        listOf(1, 2, 3).forEach { month ->
            val earlier = controller.book(AppointmentRequest("P1", "1234567890", LocalDateTime.of(2025, month, 10, 9, 0)))
            controller.complete(earlier.id)
        }
        val today = controller.book(AppointmentRequest("P1", "1234567890", visitTime))

        val bill = controller.bill(today.id)

        assertEquals(BigDecimal("1500"), bill.consultationFee)
        assertEquals(3, bill.discountPercent)
        assertEquals(BigDecimal("1629.60"), bill.totalAmount)
        assertEquals(BigDecimal("1466.64"), bill.insuranceApprovedAmount)
        assertEquals(BigDecimal("162.96"), bill.copayAmount)
    }
}