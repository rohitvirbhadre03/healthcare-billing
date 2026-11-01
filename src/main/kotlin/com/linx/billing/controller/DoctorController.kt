package com.linx.billing.controller

import com.linx.billing.dto.DoctorRequest
import com.linx.billing.dto.DoctorResponse
import com.linx.billing.service.DoctorService
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Status

@Controller("/doctors")
class DoctorController(private val doctorService: DoctorService) {

    @Post
    @Status(HttpStatus.CREATED)
    fun register(@Body request: DoctorRequest): DoctorResponse =
        DoctorResponse.from(doctorService.register(request.toDomain()))
}