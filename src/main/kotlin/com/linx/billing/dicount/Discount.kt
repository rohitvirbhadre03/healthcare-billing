package com.linx.billing.dicount

class Discount {
    fun percentage(priorVisits: Int): Int =
        minOf(priorVisits, 10)
}
