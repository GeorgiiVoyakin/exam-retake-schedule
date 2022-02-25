package ru.mirea.ikbo1319.controller

import org.springframework.web.bind.annotation.RestController
import ru.mirea.ikbo1319.service.SubjectService

@RestController("/api/schedule/subject")
class SubjectController(private val subjectService: SubjectService) {
}