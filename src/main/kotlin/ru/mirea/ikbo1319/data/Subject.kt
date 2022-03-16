package ru.mirea.ikbo1319.data

import javax.persistence.*

@Entity
@Table(name = "Subject")
data class Subject(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "subject")
    val name: String,
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false)
    val department: Department,
    @OneToMany(mappedBy = "subject", cascade = [CascadeType.ALL])
    val retakeInfos: List<RetakeInfo>,
)
