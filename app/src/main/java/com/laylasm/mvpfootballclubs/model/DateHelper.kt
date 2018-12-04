package com.laylasm.mvpfootballclubs.model

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    fun formatDateToMatch(date: Date): String {
        return SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault()).format(date)
    }
}