package ru.mirea.ikbo1319.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import ru.mirea.ikbo1319.service.DepartmentService

@RestController
class DepartmentController(private val departmentService: DepartmentService) {
    @PostMapping("/api/schedule/department")
    fun addDepartment() {

    }

    @DeleteMapping("/api/schedule/department/{id}")
    fun deleteById(@PathVariable id: Long) {
        departmentService.deleteById(id)
    }
}