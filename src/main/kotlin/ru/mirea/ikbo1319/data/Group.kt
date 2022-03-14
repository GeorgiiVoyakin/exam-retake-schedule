package ru.mirea.ikbo1319.data

import javax.persistence.*

@Entity
@Table(name = "`Group`")
data class Group(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long?,
    @Column(unique = true) val name: String
)
