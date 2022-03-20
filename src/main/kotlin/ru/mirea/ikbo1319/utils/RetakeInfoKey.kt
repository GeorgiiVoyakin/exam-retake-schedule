package ru.mirea.ikbo1319.utils

import ru.mirea.ikbo1319.dto.RetakeInfoDTO

data class RetakeInfoKey(
    val date: String,
    val time: String,
    val place: String,
    val note: String?,
    val teacher: String,
    val subject: String,
    val department: String,
) {
    constructor(retakeInfoDTO: RetakeInfoDTO) : this(
        retakeInfoDTO.date,
        retakeInfoDTO.time,
        retakeInfoDTO.place,
        retakeInfoDTO.note,
        retakeInfoDTO.teacher,
        retakeInfoDTO.subject,
        retakeInfoDTO.department,
    )
}
