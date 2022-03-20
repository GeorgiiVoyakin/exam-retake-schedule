package ru.mirea.ikbo1319.service

import org.springframework.stereotype.Service
import ru.mirea.ikbo1319.data.Department
import ru.mirea.ikbo1319.repository.DepartmentRepository

@Service
class DepartmentService(private val departmentRepository: DepartmentRepository) {
    fun findAll(): List<Department> {
        return departmentRepository.findAll()
    }

    fun findById(id: Long): Department? {
        return departmentRepository.findById(id).orElse(null)
    }

    fun addDepartment(department: Department) {
        departmentRepository.save(department)
    }

    fun save(department: Department) {
        departmentRepository.save(department)
    }

    fun deleteById(id: Long) {
        departmentRepository.deleteById(id)
    }

    fun getByName(name: String): Department? {
        return departmentRepository.getByName(name)
    }
}