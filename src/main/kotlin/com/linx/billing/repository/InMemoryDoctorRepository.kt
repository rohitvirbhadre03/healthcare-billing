package com.linx.billing.repository

import com.linx.billing.model.Doctor
import java.util.concurrent.ConcurrentHashMap

class InMemoryDoctorRepository : DoctorRepository {

    private val doctors = ConcurrentHashMap<String, Doctor>()

    override fun save(doctor: Doctor): Doctor {
        doctors[doctor.npi] = doctor
        return doctor
    }

    override fun findByNpi(npi: String): Doctor? = doctors[npi]
}