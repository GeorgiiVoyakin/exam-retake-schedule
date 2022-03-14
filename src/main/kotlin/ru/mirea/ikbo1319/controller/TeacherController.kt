package ru.mirea.ikbo1319.controller

import org.springframework.web.bind.annotation.RestController
import ru.mirea.ikbo1319.repository.TeacherRepository

@RestController
class TeacherController(private val teacherRepository: TeacherRepository)