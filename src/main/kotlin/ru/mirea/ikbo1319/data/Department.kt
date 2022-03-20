package ru.mirea.ikbo1319.data

import javax.persistence.*

@Entity
@Table(name = "Department")
data class Department(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(unique = true, name = "department")
    val name: String,
) {
    @OneToMany(mappedBy = "department", cascade = [CascadeType.ALL])
    val subjects: MutableSet<Subject> = HashSet()
}
