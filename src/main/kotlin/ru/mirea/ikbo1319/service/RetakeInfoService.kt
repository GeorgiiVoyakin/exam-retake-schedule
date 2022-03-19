package ru.mirea.ikbo1319.service

import org.springframework.stereotype.Service
import ru.mirea.ikbo1319.data.RetakeInfo
import ru.mirea.ikbo1319.exception.IllegalRequestValue
import ru.mirea.ikbo1319.repository.RetakeInfoRepository

@Service
class RetakeInfoService(private val retakeInfoRepository: RetakeInfoRepository) {
    fun findByGroupCourse(course: Int): List<RetakeInfo> {
        if (course > 4 || course < 1) {
            throw IllegalRequestValue("Illegal course value: $course")
        }
        return retakeInfoRepository.findByGroupCourse(course)
    }
}