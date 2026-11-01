package com.linx.billing.discount

class Discount {
    fun percentage(priorVisits: Int): Int =
        minOf(priorVisits, 10)
}
