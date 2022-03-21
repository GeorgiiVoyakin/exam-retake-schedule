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
    var date: LocalDate,
    var time: LocalTime,
    var place: String,
    @Column(columnDefinition = "text")
    var note: String?,
    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", nullable = false)
    var teacher: Teacher,
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    var group: Group,
    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id", nullable = false)
    var subject: Subject,
)
