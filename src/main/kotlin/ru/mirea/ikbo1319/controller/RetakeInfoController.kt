package ru.mirea.ikbo1319.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.mirea.ikbo1319.data.RetakeInfo
import ru.mirea.ikbo1319.dto.ResponseRetakeInfoDTO
import ru.mirea.ikbo1319.dto.RetakeInfoDTO
import ru.mirea.ikbo1319.exception.IllegalRequestValue
import ru.mirea.ikbo1319.service.GroupService
import ru.mirea.ikbo1319.service.RetakeInfoService
import ru.mirea.ikbo1319.service.SubjectService
import ru.mirea.ikbo1319.service.TeacherService
import ru.mirea.ikbo1319.utils.RetakeInfoKey
import ru.mirea.ikbo1319.utils.createRetakeInfoDTO
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RestController
class RetakeInfoController(
    private val retakeInfoService: RetakeInfoService,
    private val groupService: GroupService,
    private val teacherService: TeacherService,
    private val subjectService: SubjectService,
) {
    val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val timeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME

    @GetMapping("/api/schedule/retake_info/all")
    fun getAll(): ResponseEntity<List<ResponseRetakeInfoDTO>> {
        val retakeInfos = retakeInfoService.findAll()
        val response = mutableListOf<ResponseRetakeInfoDTO>()
        for (info in retakeInfos) {
            response.add(ResponseRetakeInfoDTO(info))
        }
        return ResponseEntity.ok(response)
    }

    @GetMapping("/api/schedule/retake_info/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Any> {
        val retakeInfo = retakeInfoService.findById(id)
        return if (retakeInfo != null) {
            ResponseEntity.ok(ResponseRetakeInfoDTO(retakeInfo))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/api/schedule/retake_info/add")
    fun addRetakeInfo(
        @RequestParam(name = "date") dateString: String,
        @RequestParam(name = "time") timeString: String,
        @RequestParam place: String,
        @RequestParam note: String?,
        @RequestParam(name = "teacher") teacherName: String,
        @RequestParam(name = "group") groupName: String,
        @RequestParam(name = "subject") subjectName: String
    ): ResponseEntity<Any> {
        val group = groupService.findByName(groupName) ?: return ResponseEntity.status(304).build()
        val teacher = teacherService.getByName(teacherName) ?: return ResponseEntity.status(304).build()
        val subject = subjectService.getByName(subjectName) ?: return ResponseEntity.status(304).build()
        val date = LocalDate.parse(dateString, dateFormatter)
        val time = LocalTime.parse(timeString, timeFormatter)
        return if (retakeInfoService.find(
                date,
                time,
                place,
                note,
                teacher,
                group,
                subject,
                ) != null
        ) {
            ResponseEntity.status(304).build()
        } else {
            val retakeInfo = RetakeInfo(
                null,
                date,
                time,
                place,
                note,
                teacher,
                group,
                subject,
            )
            retakeInfoService.save(retakeInfo)
            ResponseEntity.ok(ResponseRetakeInfoDTO(retakeInfo))
        }
    }

    @PatchMapping("/api/schedule/retake_info/{id}")
    fun updateRetakeInfo(
        @PathVariable id: Long,
        @RequestParam(name = "date") dateString: String?,
        @RequestParam(name = "time") timeString: String?,
        @RequestParam place: String?,
        @RequestParam note: String?,
        @RequestParam(name = "teacher") teacherName: String?,
        @RequestParam(name = "group") groupName: String?,
        @RequestParam(name = "subject") subjectName: String?,
    ): ResponseEntity<Any> {
        val retakeInfo = retakeInfoService.findById(id) ?: return ResponseEntity.notFound().build()
        val teacher = teacherName?.let { teacherService.getByName(it) }
        val group = groupName?.let { groupService.findByName(it) }
        val subject = subjectName?.let { subjectService.getByName(it) }
        val date = if (dateString == null) null else LocalDate.parse(dateString, dateFormatter)
        val time = if (timeString == null) null else LocalTime.parse(timeString, timeFormatter)
        retakeInfo.date = date ?: retakeInfo.date
        retakeInfo.time = time ?: retakeInfo.time
        retakeInfo.place = place ?: retakeInfo.place
        retakeInfo.note = note
        retakeInfo.teacher = teacher ?: retakeInfo.teacher
        retakeInfo.group = group ?: retakeInfo.group
        retakeInfo.subject = subject ?: retakeInfo.subject
        retakeInfoService.save(retakeInfo)
        return ResponseEntity.ok(ResponseRetakeInfoDTO(retakeInfo))
    }

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

    @DeleteMapping("/api/schedule/retake_info/{id}")
    fun deleteById(@PathVariable id: Long) {
        if (retakeInfoService.findById(id) == null) {
            return
        }
        retakeInfoService.deleteById(id)
    }
}
