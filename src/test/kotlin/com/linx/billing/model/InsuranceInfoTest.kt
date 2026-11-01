package com.linx.billing.model

import kotlin.test.Test
import kotlin.test.assertFailsWith

class InsuranceInfoTest {

    @Test
    fun `bin is required`() {
        assertFailsWith<IllegalArgumentException> {
            InsuranceInfo(bin = " ", pcn = "M123", memberId = "M123456")
        }
    }

    @Test
    fun `pcn is required`() {
        assertFailsWith<IllegalArgumentException> {
            InsuranceInfo(bin = "610014", pcn = "", memberId = "M123456")
        }
    }

    @Test
    fun `member id is required`() {
        assertFailsWith<IllegalArgumentException> {
            InsuranceInfo(bin = "610014", pcn = "M123", memberId = "")
        }
    }
}