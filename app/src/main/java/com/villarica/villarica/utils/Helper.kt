package com.villarica.villarica.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object Helper {

    /**
     * Calculates the age based on a birth date string.
     *
     * @param birthDateString The date of birth in "yyyy-MM-dd" format.
     * @return The calculated age in years, or null if the date format is invalid.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun calculateAge(birthDateString: String): Int? {
        return try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val birthDate = LocalDate.parse(birthDateString, formatter)
            val currentDate = LocalDate.now()
            Period.between(birthDate, currentDate).years
        } catch (e: DateTimeParseException) {
            println("Error: Invalid date format. Please use 'yyyy-MM-dd'.")
            null
        }
    }

    fun formatPakistaniNumber(number: String): String {
        val digits = number.filter { it.isDigit() }

        return if (digits.length == 11) {
            "${digits.substring(0, 4)} ${digits.substring(4, 7)} ${digits.substring(7)}"
        } else {
            number // Return as-is if not valid length
        }
    }

}