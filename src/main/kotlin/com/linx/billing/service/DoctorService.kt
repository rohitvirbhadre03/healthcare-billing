package com.linx.billing.service

import com.linx.billing.model.Doctor
import com.linx.billing.repository.DoctorRepository

class DoctorService(private val doctorRepository: DoctorRepository) {

    fun register(doctor: Doctor): Doctor {
        check(doctorRepository.findByNpi(doctor.npi) == null) {
            "A doctor with NPI ${doctor.npi} is already exists"
        }
        return doctorRepository.save(doctor)
    }
}