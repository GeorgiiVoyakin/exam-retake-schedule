package ru.mirea.ikbo1319.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import ru.mirea.ikbo1319.service.DepartmentService

@RestController("/api/schedule/department")
class DepartmentController(private val departmentService: DepartmentService) {
    @PostMapping
    fun addDepartment() {

    }
}