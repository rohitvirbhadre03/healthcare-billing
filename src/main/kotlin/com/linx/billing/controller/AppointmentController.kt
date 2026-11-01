package com.linx.billing.controller

import com.linx.billing.dto.AppointmentRequest
import com.linx.billing.dto.AppointmentResponse
import com.linx.billing.service.AppointmentService
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Status

@Controller("/appointments")
class AppointmentController(private val appointmentService: AppointmentService) {

    @Post
    @Status(HttpStatus.CREATED)
    fun book(@Body request: AppointmentRequest): AppointmentResponse =
        AppointmentResponse.from(appointmentService.book(request.patientId, request.doctorNpi, request.scheduledAt))

    @Post("/{id}/complete")
    fun complete(@PathVariable id: String): AppointmentResponse =
        AppointmentResponse.from(appointmentService.complete(id))

    @Post("/{id}/cancel")
    fun cancel(@PathVariable id: String): AppointmentResponse =
        AppointmentResponse.from(appointmentService.cancel(id))
}