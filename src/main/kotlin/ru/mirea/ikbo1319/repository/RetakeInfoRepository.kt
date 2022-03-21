package ru.mirea.ikbo1319.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.mirea.ikbo1319.data.Group
import ru.mirea.ikbo1319.data.RetakeInfo
import ru.mirea.ikbo1319.data.Subject
import ru.mirea.ikbo1319.data.Teacher
import java.time.LocalDate
import java.time.LocalTime

interface RetakeInfoRepository : JpaRepository<RetakeInfo, Long> {
    fun findByGroupCourse(course: Int): List<RetakeInfo>
    fun findByDateAndTimeAndPlaceAndNoteAndTeacherAndGroupAndSubject(
        date: LocalDate,
        time: LocalTime,
        place: String,
        note: String?,
        teacher: Teacher,
        group: Group,
        subject: Subject,
    ): RetakeInfo?
}