package ru.mirea.ikbo1319.dto

import ru.mirea.ikbo1319.data.Department

data class DepartmentDTO(var id: Long, var name: String) {
    constructor(department: Department) : this(department.id!!, department.name)
}
