package com.linx.billing.repository

import com.linx.billing.model.Doctor
import jakarta.inject.Singleton
import java.util.concurrent.ConcurrentHashMap

@Singleton
class InMemoryDoctorRepository : DoctorRepository {

    private val doctors = ConcurrentHashMap<String, Doctor>()

    override fun save(doctor: Doctor): Doctor {
        doctors[doctor.npi] = doctor
        return doctor
    }

    override fun findByNpi(npi: String): Doctor? = doctors[npi]
}