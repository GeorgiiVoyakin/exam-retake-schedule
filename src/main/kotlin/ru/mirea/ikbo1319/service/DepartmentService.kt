package ru.mirea.ikbo1319.service

import org.springframework.stereotype.Service
import ru.mirea.ikbo1319.repository.DepartmentRepository

@Service
class DepartmentService(private val departmentRepository: DepartmentRepository) {
    fun addDepartment() {

    }

    fun deleteById(id: Long) {
        departmentRepository.deleteById(id)
    }
}