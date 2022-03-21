package ru.mirea.ikbo1319.service

import org.springframework.stereotype.Service
import ru.mirea.ikbo1319.data.Group
import ru.mirea.ikbo1319.data.RetakeInfo
import ru.mirea.ikbo1319.data.Subject
import ru.mirea.ikbo1319.data.Teacher
import ru.mirea.ikbo1319.exception.IllegalRequestValue
import ru.mirea.ikbo1319.repository.RetakeInfoRepository
import java.time.LocalDate
import java.time.LocalTime

@Service
class RetakeInfoService(private val retakeInfoRepository: RetakeInfoRepository) {
    fun findByGroupCourse(course: Int): List<RetakeInfo> {
        if (course > 4 || course < 1) {
            throw IllegalRequestValue("Illegal course value: $course")
        }
        return retakeInfoRepository.findByGroupCourse(course)
    }

    fun findAll(): List<RetakeInfo> {
        return retakeInfoRepository.findAll()
    }

    fun findById(id: Long): RetakeInfo? {
        return retakeInfoRepository.findById(id).orElse(null)
    }

    fun find(
        date: LocalDate,
        time: LocalTime,
        place: String,
        note: String?,
        teacher: Teacher,
        group: Group,
        subject: Subject,
    ): RetakeInfo? {
        return retakeInfoRepository.findByDateAndTimeAndPlaceAndNoteAndTeacherAndGroupAndSubject(
            date, time, place, note, teacher, group, subject
        )
    }

    fun deleteById(id: Long) {
        retakeInfoRepository.deleteById(id)
    }

    fun save(retakeInfo: RetakeInfo) {
        retakeInfoRepository.save(retakeInfo)
    }
}