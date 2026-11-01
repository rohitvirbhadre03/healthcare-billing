package com.linx.billing.controller

import com.linx.billing.dto.PatientRequest
import com.linx.billing.dto.PatientResponse
import com.linx.billing.service.PatientService
import com.linx.billing.util.parseUsDate
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Status

@Controller("/patients")
class PatientController(private val patientService: PatientService) {

    @Post
    @Status(HttpStatus.CREATED)
    fun register(@Body request: PatientRequest): PatientResponse {
        val patient = patientService.register(
            firstName = request.firstName,
            lastName = request.lastName,
            dateOfBirth = parseUsDate(request.dateOfBirth),
            insurance = request.insurance.toDomain(),
        )
        return PatientResponse.from(patient, patientService.ageToday(patient))
    }
}