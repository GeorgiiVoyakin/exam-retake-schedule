package ru.mirea.ikbo1319.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.mirea.ikbo1319.data.Department
import ru.mirea.ikbo1319.dto.DepartmentDTO
import ru.mirea.ikbo1319.service.DepartmentService

@RestController
class DepartmentController(private val departmentService: DepartmentService) {
    @GetMapping("/api/schedule/department")
    fun getAll(): ResponseEntity<List<DepartmentDTO>> {
        val departments = departmentService.findAll()
        val response = mutableListOf<DepartmentDTO>()
        for (department in departments) {
            response.add(DepartmentDTO(department))
        }
        return ResponseEntity.ok(response)
    }

    @GetMapping("/api/schedule/department/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Any> {
        val department = departmentService.findById(id)
        return if (department != null) {
            ResponseEntity.ok(DepartmentDTO(department))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/api/schedule/department")
    fun addDepartment(@RequestParam name: String): ResponseEntity<Any> {
        return if (departmentService.getByName(name) != null) {
            ResponseEntity.status(304).build()
        } else {
            val department = Department(null, name)
            departmentService.addDepartment(department)
            ResponseEntity.ok(DepartmentDTO(department))
        }
    }

    @PatchMapping("/api/schedule/department/{id}")
    fun updateDepartment(@PathVariable id: Long, @RequestParam name: String): ResponseEntity<Any> {
        if (departmentService.getByName(name) != null) {
            return ResponseEntity.status(304).build()
        }
        val department = departmentService.findById(id)
        return if (department != null) {
            department.name = name
            departmentService.save(department)
            ResponseEntity.ok(DepartmentDTO(department))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/api/schedule/department/{id}")
    fun deleteById(@PathVariable id: Long) {
        if (departmentService.findById(id) == null) {
            return
        }
        departmentService.deleteById(id)
    }
}