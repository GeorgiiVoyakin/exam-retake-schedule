package ru.mirea.ikbo1319.dto

import ru.mirea.ikbo1319.data.RetakeInfo
import java.time.format.DateTimeFormatter

data class RetakeInfoDTO(
    var date: String,
    var time: String,
    var place: String,
    var note: String?,
    var teacher: String,
    var group: List<String>?,
    var subject: String,
    var department: String,
) {
    constructor(retakeInfo: RetakeInfo) : this(
        retakeInfo.date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
        retakeInfo.time.format(DateTimeFormatter.ofPattern("hh:mm")),
        retakeInfo.place,
        retakeInfo.note,
        retakeInfo.teacher.name,
        listOf(retakeInfo.group.name),
        retakeInfo.subject.name,
        retakeInfo.subject.department.name,
    )
}