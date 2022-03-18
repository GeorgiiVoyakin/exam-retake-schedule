package ru.mirea.ikbo1319.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.mirea.ikbo1319.data.RetakeInfo
import ru.mirea.ikbo1319.dto.RetakeInfoDTO
import ru.mirea.ikbo1319.service.RetakeInfoService
import ru.mirea.ikbo1319.utils.createRetakeInfoDTO

@RestController
class RetakeInfoController(private val retakeInfoService: RetakeInfoService) {
    @GetMapping("/api/schedule/retake_info")
    fun getByGroupCourse(@RequestParam course: Int): ResponseEntity<Any> {
        return try {
            val retakeInfos = retakeInfoService.findByGroupCourse(course)
            val retakeInfoDTOs = mutableListOf<RetakeInfoDTO>()
            for (info in retakeInfos) {
                retakeInfoDTOs.add(createRetakeInfoDTO(info))
            }

            ResponseEntity.ok(retakeInfoDTOs)
        } catch(e: Exception) {
            ResponseEntity.notFound().build()
        }
    }
}
