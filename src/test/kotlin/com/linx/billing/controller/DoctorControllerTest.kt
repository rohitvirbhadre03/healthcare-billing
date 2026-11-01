package com.linx.billing.controller

import com.linx.billing.dto.DoctorRequest
import com.linx.billing.dto.DoctorResponse
import com.linx.billing.pricing.Speciality
import com.linx.billing.repository.InMemoryDoctorRepository
import com.linx.billing.service.DoctorService
import kotlin.test.Test
import kotlin.test.assertEquals

class DoctorControllerTest {

    private val controller = DoctorController(DoctorService(InMemoryDoctorRepository()))

    @Test
    fun `registering a doctor returns the saved doctor`() {
        val response = controller.register(DoctorRequest("Nisha", "Rao", "1234567890", Speciality.CARDIO, "06/15/2000"))
        assertEquals(DoctorResponse("Nisha", "Rao", "1234567890", Speciality.CARDIO, "06/15/2000"), response)
    }
}