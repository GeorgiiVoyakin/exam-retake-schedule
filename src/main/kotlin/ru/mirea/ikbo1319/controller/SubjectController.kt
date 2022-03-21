package ru.mirea.ikbo1319.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.mirea.ikbo1319.data.Subject
import ru.mirea.ikbo1319.dto.SubjectDTO
import ru.mirea.ikbo1319.service.DepartmentService
import ru.mirea.ikbo1319.service.SubjectService

@RestController("/api/schedule/subject")
class SubjectController(private val subjectService: SubjectService, private val departmentService: DepartmentService) {
    @GetMapping("/api/schedule/subject")
    fun getAll(): ResponseEntity<List<SubjectDTO>> {
        val subjects = subjectService.findAll()
        val response = mutableListOf<SubjectDTO>()
        for (subject in subjects) {
            response.add(SubjectDTO(subject))
        }
        return ResponseEntity.ok(response)
    }

    @GetMapping("/api/schedule/subject/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Any> {
        val subject = subjectService.findById(id)
        return if (subject != null) {
            ResponseEntity.ok(SubjectDTO(subject))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/api/schedule/subject")
    fun addSubject(
        @RequestParam name: String,
        @RequestParam(name = "department") departmentName: String
    ): ResponseEntity<Any> {
        return if (subjectService.getByName(name) != null) {
            ResponseEntity.status(304).build()
        } else {
            val department = departmentService.getByName(departmentName) ?: return ResponseEntity.status(304).build()
            val subject = Subject(null, name, department, emptyList())
            subjectService.addSubject(subject)
            ResponseEntity.ok(SubjectDTO(subject))
        }
    }

    @PatchMapping("/api/schedule/subject/{id}")
    fun updateSubject(
        @PathVariable id: Long,
        @RequestParam name: String?,
        @RequestParam(name = "department") departmentName: String?,
    ): ResponseEntity<Any> {
        if (name != null) {
            if (subjectService.getByName(name) != null) {
                return ResponseEntity.status(304).build()
            }
        }
        val department = departmentName?.let { departmentService.getByName(it) }
        val subject = subjectService.findById(id)
        return if (subject != null) {
            subject.name = name ?: subject.name
            subject.department = department ?: subject.department
            subjectService.addSubject(subject)
            ResponseEntity.ok(SubjectDTO(subject))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/api/schedule/subject/{id}")
    fun deleteById(@PathVariable id: Long) {
        if (subjectService.findById(id) == null) {
            return
        }
        subjectService.deleteById(id)
    }
}