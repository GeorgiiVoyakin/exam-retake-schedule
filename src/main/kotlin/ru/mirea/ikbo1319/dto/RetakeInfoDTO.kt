package ru.mirea.ikbo1319.dto

import ru.mirea.ikbo1319.data.RetakeInfo
import java.time.LocalDate
import java.time.LocalTime

data class RetakeInfoDTO(
    var date: LocalDate,
    var time: LocalTime,
    var place: String,
    var note: String,
    var teacher: String,
    var group: String,
    var subject: String,
) {
    constructor(retakeInfo: RetakeInfo) : this(
        retakeInfo.date,
        retakeInfo.time,
        retakeInfo.place,
        retakeInfo.note,
        retakeInfo.teacher.name,
        retakeInfo.group.name,
        retakeInfo.subject.name,
    )
}