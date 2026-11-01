package com.linx.billing.service

import com.linx.billing.discount.Discount
import com.linx.billing.insurance.Insurance
import com.linx.billing.invoice.BillCalculator
import com.linx.billing.model.Appointment
import com.linx.billing.model.AppointmentStatus
import com.linx.billing.model.Doctor
import com.linx.billing.pricing.FeeStructure
import com.linx.billing.pricing.Speciality
import com.linx.billing.repository.InMemoryAppointmentRepository
import com.linx.billing.repository.InMemoryDoctorRepository
import com.linx.billing.tax.Gst
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class BillingServiceTest {

    private val appointments = InMemoryAppointmentRepository()
    private val doctors = InMemoryDoctorRepository()
    private val calculator = BillCalculator(FeeStructure(), Discount(), Gst(), Insurance())
    private val service = BillingService(appointments, doctors, calculator)

    private val visitTime = LocalDateTime.of(2025, 6, 15, 10, 30)

    @BeforeTest
    fun registerDoctor() {
        doctors.save(Doctor("Anil", "K", "1234567890", Speciality.CARDIO, LocalDate.of(2000, 6, 15)))
    }

    @Test
    fun `generate bill with discount for completed visit`() {
        appointments.save(appointment("A1", LocalDateTime.of(2025, 1, 10, 9, 0), AppointmentStatus.COMPLETE))
        appointments.save(appointment("A2", LocalDateTime.of(2025, 2, 10, 9, 0), AppointmentStatus.COMPLETE))
        appointments.save(appointment("A3", LocalDateTime.of(2025, 3, 10, 9, 0), AppointmentStatus.COMPLETE))
        appointments.save(appointment("A4", visitTime))

        val bill = service.generateBill("A4")

        assertEquals(3, bill.discountPercent)
        assertEquals(BigDecimal("1629.60"), bill.totalAmount)
        assertEquals(BigDecimal("1466.64"), bill.insuranceApprovedAmount)
        assertEquals(BigDecimal("162.96"), bill.copayAmount)
    }

    private fun appointment(
        id: String,
        scheduledAt: LocalDateTime,
        status: AppointmentStatus = AppointmentStatus.SCHEDULED,
    ) = Appointment(id, "P1", "1234567890", scheduledAt, status)
}