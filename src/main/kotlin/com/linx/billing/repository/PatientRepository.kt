package com.linx.billing.repository

import com.linx.billing.model.Patient

interface PatientRepository {

    fun save(patient: Patient): Patient

    fun findById(id: String): Patient?
}