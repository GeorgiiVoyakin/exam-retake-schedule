package ru.mirea.ikbo1319.dto

import ru.mirea.ikbo1319.data.RetakeInfo
import java.time.format.DateTimeFormatter

/**
RetakeInfoDTO but instead of groups list it has single group value
 **/
data class ResponseRetakeInfoDTO(
    val id: Long,
    var date: String,
    var time: String,
    var place: String,
    var note: String?,
    var teacher: String,
    var group: String,
    var subject: String,
    var department: String,
) {
    constructor(retakeInfo: RetakeInfo) : this(
        retakeInfo.id!!,
        retakeInfo.date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
        retakeInfo.time.format(DateTimeFormatter.ofPattern("hh:mm")),
        retakeInfo.place,
        retakeInfo.note,
        retakeInfo.teacher.name,
        retakeInfo.group.name,
        retakeInfo.subject.name,
        retakeInfo.subject.department.name,
    )
}
