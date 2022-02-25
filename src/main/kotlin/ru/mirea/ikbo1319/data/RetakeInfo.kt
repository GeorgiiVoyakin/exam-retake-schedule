package ru.mirea.ikbo1319.data

import java.time.LocalDate

data class RetakeInfo(
    val id: Long,
    val date: LocalDate,
    val place: String,
    val groups: List<String>,
    val teacher: String,
    val note: String
)
