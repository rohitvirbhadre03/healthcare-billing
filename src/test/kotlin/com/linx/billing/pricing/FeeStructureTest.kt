package com.linx.billing.pricing

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals

class FeeStructureTest {

    private val feeStructure = FeeStructure();

    @Test
    fun `ortho doctor with 25 years charges 1000`() {
        assertEquals(BigDecimal("1000"), feeStructure.feeFor(Speciality.ORTHO, 25))
    }

}