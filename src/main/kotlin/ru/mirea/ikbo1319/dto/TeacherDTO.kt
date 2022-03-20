package ru.mirea.ikbo1319.dto

import ru.mirea.ikbo1319.data.Teacher

data class TeacherDTO(var id: Long, var name: String) {
    constructor(teacher: Teacher) : this(teacher.id!!, teacher.name)
}
