package com.linx.billing.pricing

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals

class FeeStructureTest {

    private val feeStructure = FeeStructure();

    @Test
    fun `cardio doctor with 25 years charges 1500`() {
        assertEquals(BigDecimal("1500"), feeStructure.feeFor(Speciality.CARDIO, 25))
    }

    @Test
    fun `ortho doctor with 5 years charges 800`() {
        assertEquals(BigDecimal("800"), feeStructure.feeFor(Speciality.ORTHO, 5))
    }

    @Test
    fun `ortho doctor with exactly 20 years charges 1000`() {
        assertEquals(BigDecimal("1000"), feeStructure.feeFor(Speciality.ORTHO, 20))
    }

    @Test
    fun `ortho doctor with exactly 30 years charges 1500`() {
        assertEquals(BigDecimal("1500"), feeStructure.feeFor(Speciality.ORTHO, 30))
    }

    @Test
    fun `ortho doctor with 19 years is still in the lowest band`() {
        assertEquals(BigDecimal("800"), feeStructure.feeFor(Speciality.ORTHO, 19))
    }

    @Test
    fun `cardio doctor with less than 20 years charges 1000`() {
        assertEquals(BigDecimal("1000"), feeStructure.feeFor(Speciality.CARDIO, 10))
    }

    @Test
    fun `cardio doctor with 29 years is still in the middle band`() {
        assertEquals(BigDecimal("1500"), feeStructure.feeFor(Speciality.CARDIO, 29))
    }

    @Test
    fun `cardio doctor with more than 30 years charges 2000`() {
        assertEquals(BigDecimal("2000"), feeStructure.feeFor(Speciality.CARDIO, 35))
    }

}