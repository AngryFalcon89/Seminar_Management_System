package com.example.seminarmanagementsystem.presentation.utils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun FormatDate(inputDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("d MMM, yyyy", Locale.getDefault())

    try {
        val date = inputFormat.parse(inputDate)
        return outputFormat.format(date!!)
    } catch (e: ParseException) {
        e.printStackTrace()
        // Handle parsing error
        return ""
    }
}