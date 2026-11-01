package com.linx.billing.insurance

import com.linx.billing.insurance.Coverage
import com.linx.billing.insurance.Insurance
import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals

class InsuranceTest {

    private val insurance = Insurance()

    @Test
    fun `insurance pays 90 percent and patient pays the rest`() {
        val coverage = insurance.split(BigDecimal("1629.60"))
        assertEquals(Coverage(BigDecimal("1466.64"), BigDecimal("162.96")), coverage)
    }
}