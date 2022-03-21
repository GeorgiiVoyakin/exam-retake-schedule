package ru.mirea.ikbo1319.dto

import ru.mirea.ikbo1319.data.Subject

data class SubjectDTO(var id: Long, var name: String, var department: String) {
    constructor(subject: Subject): this(subject.id!!, subject.name, subject.department.name)
}
