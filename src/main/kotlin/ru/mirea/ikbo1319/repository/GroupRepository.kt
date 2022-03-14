package ru.mirea.ikbo1319.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.mirea.ikbo1319.data.Group

interface GroupRepository : JpaRepository<Group, Long> {
    fun findByName(name: String) : Group?
    fun deleteByName(name: String) : Long
}