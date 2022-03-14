package ru.mirea.ikbo1319.data

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Subject(@Id val id: Long, val name: String)
