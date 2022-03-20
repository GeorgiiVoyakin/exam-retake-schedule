package ru.mirea.ikbo1319.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.mirea.ikbo1319.data.RetakeInfo
import ru.mirea.ikbo1319.dto.RetakeInfoDTO
import ru.mirea.ikbo1319.exception.IllegalRequestValue
import ru.mirea.ikbo1319.service.RetakeInfoService
import ru.mirea.ikbo1319.utils.RetakeInfoKey
import ru.mirea.ikbo1319.utils.createRetakeInfoDTO

@RestController
class RetakeInfoController(private val retakeInfoService: RetakeInfoService) {
    @CrossOrigin
    @GetMapping("/api/schedule/retake_info")
    fun getByGroupCourse(@RequestParam course: Int): ResponseEntity<Any> {
        return try {
            val retakeInfos: List<RetakeInfo> = retakeInfoService.findByGroupCourse(course)
            val retakeInfoDTOs = mutableListOf<RetakeInfoDTO>()

            val groupLists = HashMap<RetakeInfoKey, MutableList<String>>()

            for (info in retakeInfos) {
                val retakeInfoDTO = createRetakeInfoDTO(info)
                val key = RetakeInfoKey(retakeInfoDTO)
                if (groupLists.containsKey(key)) {
                    if (groupLists[key] == null) groupLists[key] =
                        mutableListOf(info.group.name) else groupLists[key]?.add(info.group.name)
                } else {
                    groupLists[key] = mutableListOf(info.group.name)
                }
            }

            for (element in groupLists) {
                retakeInfoDTOs.add(
                    RetakeInfoDTO(
                        element.key.date,
                        element.key.time,
                        element.key.place,
                        element.key.note,
                        element.key.teacher,
                        groupLists[element.key],
                        element.key.subject,
                        element.key.department,
                    )
                )
            }

            ResponseEntity.ok(retakeInfoDTOs)
        } catch (e: IllegalRequestValue) {
            ResponseEntity.notFound().build()
        }
    }
}
