package com.linx.billing.repository

import com.linx.billing.model.Doctor

interface DoctorRepository {

    fun save(doctor: Doctor): Doctor

    fun findByNpi(npi: String): Doctor?
}