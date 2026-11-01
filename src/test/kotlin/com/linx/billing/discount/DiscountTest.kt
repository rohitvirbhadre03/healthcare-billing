package com.linx.billing.discount

import kotlin.test.Test
import kotlin.test.assertEquals

class DiscountTest {

    private val discount = Discount();

    @Test
    fun `no discount for no prior visits`() {
        assertEquals(0, discount.percentage(priorVisits = 0))
    }

    @Test
    fun `max discount is 10 percent`() {
        assertEquals(10, discount.percentage(priorVisits = 14))
    }
}