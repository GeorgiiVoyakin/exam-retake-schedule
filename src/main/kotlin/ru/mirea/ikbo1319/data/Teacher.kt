package ru.mirea.ikbo1319.data

import javax.persistence.*

@Entity
@Table(name = "Teacher")
data class Teacher(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "teacher")
    val name: String,
    @OneToMany(mappedBy = "teacher", cascade = [CascadeType.ALL])
    val retakeInfos: List<RetakeInfo>,
)
