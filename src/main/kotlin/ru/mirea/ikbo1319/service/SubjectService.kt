package ru.mirea.ikbo1319.service

import org.springframework.stereotype.Service
import ru.mirea.ikbo1319.data.Subject
import ru.mirea.ikbo1319.repository.SubjectRepository

@Service
class SubjectService(private val subjectRepository: SubjectRepository) {
    fun addSubject(subject: Subject) {
        subjectRepository.save(subject)
    }

    fun getByName(name: String): Subject? {
        return subjectRepository.getByName(name)
    }

    fun findById(id: Long): Subject? {
        return subjectRepository.findById(id).orElse(null)
    }

    fun findAll(): List<Subject> {
        return subjectRepository.findAll()
    }

    fun deleteById(id: Long) {
        subjectRepository.deleteById(id)
    }
}