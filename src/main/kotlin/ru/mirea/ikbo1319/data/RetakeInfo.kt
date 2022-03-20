package ru.mirea.ikbo1319.data

import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.*

@Entity
@Table(name = "Schedule")
data class RetakeInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val date: LocalDate,
    val time: LocalTime,
    val place: String,
    @Column(columnDefinition = "text")
    val note: String,
    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", nullable = false)
    val teacher: Teacher,
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    val group: Group,
    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id", nullable = false)
    val subject: Subject,
)
