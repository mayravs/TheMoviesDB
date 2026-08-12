package com.example.themoviesdb.ui

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateUtils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatReleaseDate(
        isoDate: String
    ) : String {
        return try {
            val date = LocalDate.parse(isoDate)

            val formatter = DateTimeFormatter
                .ofPattern("MMMM d, yyyy")
                .withLocale(Locale.US)

            date.format(formatter)
        } catch (_: Exception) {
            isoDate
        }
    }
}