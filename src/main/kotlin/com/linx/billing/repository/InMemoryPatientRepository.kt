package com.linx.billing.repository

import com.linx.billing.model.Patient
import jakarta.inject.Singleton
import java.util.concurrent.ConcurrentHashMap

@Singleton
class InMemoryPatientRepository : PatientRepository {

    private val patients = ConcurrentHashMap<String, Patient>()

    override fun save(patient: Patient): Patient {
        patients[patient.id] = patient
        return patient
    }

    override fun findById(id: String): Patient? = patients[id]
}