package com.pivnoydevelopment.common.utils

import java.text.SimpleDateFormat
import java.util.*

object DateConverter {

    private val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale("ru"))

    fun convertDate(input: String): String {
        return try {
            val date = inputFormat.parse(input)
            val formatted = outputFormat.format(date!!)
            val parts = formatted.split(" ")
            if (parts.size == 3) {
                val day = parts[0]
                val month = parts[1].replaceFirstChar { it.uppercaseChar() }
                val year = parts[2]
                "$day $month $year"
            } else {
                formatted
            }
        } catch (e: Exception) {
            input
        }
    }
}