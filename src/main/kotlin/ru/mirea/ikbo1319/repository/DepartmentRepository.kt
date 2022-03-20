package ru.mirea.ikbo1319.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.mirea.ikbo1319.data.Department

interface DepartmentRepository : JpaRepository<Department, Long> {
    fun getByName(name: String): Department?
}