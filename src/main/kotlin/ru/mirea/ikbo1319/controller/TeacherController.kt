package ru.mirea.ikbo1319.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.mirea.ikbo1319.data.Teacher
import ru.mirea.ikbo1319.dto.TeacherDTO
import ru.mirea.ikbo1319.service.TeacherService

@RestController
class TeacherController(private val teacherService: TeacherService) {
    @GetMapping("/api/schedule/teacher")
    fun getAll(): ResponseEntity<List<TeacherDTO>> {
        val teachers = teacherService.findAll()
        val response = mutableListOf<TeacherDTO>()
        for (teacher in teachers) {
            response.add(TeacherDTO(teacher))
        }
        return ResponseEntity.ok(response)
    }

    @GetMapping("/api/schedule/teacher/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Any> {
        val teacher = teacherService.findById(id)
        return if (teacher != null) {
            ResponseEntity.ok(TeacherDTO(teacher))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/api/schedule/teacher")
    fun addTeacher(@RequestParam name: String): ResponseEntity<Any> {
        return if (teacherService.getByName(name) != null) {
            ResponseEntity.status(304).build()
        } else {
            val teacher = Teacher(null, name, emptyList())
            teacherService.save(teacher)
            ResponseEntity.ok(TeacherDTO(teacher))
        }
    }

    @PatchMapping("/api/schedule/teacher/{id}")
    fun updateTeacher(@PathVariable id: Long, @RequestParam name: String): ResponseEntity<Any> {
        if (teacherService.getByName(name) != null) {
            return ResponseEntity.status(304).build()
        }
        val teacher = teacherService.findById(id)
        return if (teacher != null) {
            teacher.name = name
            teacherService.save(teacher)
            ResponseEntity.ok(TeacherDTO(teacher))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/api/schedule/teacher/{id}")
    fun deleteById(@PathVariable id: Long) {
        if (teacherService.findById(id) == null) {
            return
        }
        teacherService.deleteById(id)
    }
}