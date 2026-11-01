package com.linx.billing.util

import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

class DateFormatsTest {

    @Test
    fun `parses date in MM-DD-YYYY`() {
        assertEquals(LocalDate.of(1990, 3, 15), parseUsDate("03/15/1990"))
    }

    @Test
    fun `formats date as MM-DD-YYYY`() {
        assertEquals("03/05/1990", formatUsDate(LocalDate.of(1990, 3, 5)))
    }

}