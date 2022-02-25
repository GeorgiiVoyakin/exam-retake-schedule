package ru.mirea.ikbo1319.service

import org.springframework.stereotype.Service
import ru.mirea.ikbo1319.repository.SubjectRepository

@Service
class SubjectService(private val subjectRepository: SubjectRepository) {
}