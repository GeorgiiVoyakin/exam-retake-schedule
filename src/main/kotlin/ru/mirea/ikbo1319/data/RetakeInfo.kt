package ru.mirea.ikbo1319.data

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class RetakeInfo(
    @Id val id: Long,
    val date: LocalDate,
    val place: String,
//    @OneToMany val groups: List<String>,
    val teacher: String,
    val note: String
)
