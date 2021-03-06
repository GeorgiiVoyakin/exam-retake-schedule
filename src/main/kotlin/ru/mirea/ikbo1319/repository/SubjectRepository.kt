package ru.mirea.ikbo1319.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.mirea.ikbo1319.data.Subject

interface SubjectRepository : JpaRepository<Subject, Long> {
    fun getByName(name: String): Subject?
}