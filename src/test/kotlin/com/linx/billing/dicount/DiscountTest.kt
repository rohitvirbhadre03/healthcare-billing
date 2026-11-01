package com.linx.billing.dicount

import kotlin.test.Test
import kotlin.test.assertEquals

class DiscountTest {

    private val discount = Discount();

    @Test
    fun `no discount for no prior visits`() {
        assertEquals(0, discount.percentage(priorVisits = 0))
    }
}