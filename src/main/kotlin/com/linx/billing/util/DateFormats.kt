package com.linx.billing.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.format.ResolverStyle

private val US_DATE: DateTimeFormatter =
    DateTimeFormatter.ofPattern("MM/dd/uuuu").withResolverStyle(ResolverStyle.STRICT)

fun parseUsDate(value: String): LocalDate =
    try {
        LocalDate.parse(value, US_DATE)
    } catch (e: DateTimeParseException) {
        throw IllegalArgumentException("Date must be a real date in MM/DD/YYYY format: $value", e)
    }

fun formatUsDate(date: LocalDate): String = date.format(US_DATE)